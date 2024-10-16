package app.eshop.security;
import app.eshop.dto.AuthRequestDTO;
import app.eshop.dto.RegistrationDTO;
import app.eshop.dto.UserDTO;
import app.eshop.dto.UserPasswordDTO;
import app.eshop.entity.MyUser;
import app.eshop.service.EmailService;
import app.eshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtilities jwtUtilities;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        final UserDetails userDetails = userService.getUserByUsername(authRequestDTO.getUsername());
        if(userDetails != null && passwordEncoder.matches(authRequestDTO.getPassword(), userDetails.getPassword())){
            return ResponseEntity.ok(jwtUtilities.generateToken(userDetails));
        }
        return ResponseEntity.status(400).body("Nastala chyba");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO){
        userService.createUser(registrationDTO);
        return ResponseEntity.ok("čupr si zaregistrovan");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> changedPassword(@RequestBody UserPasswordDTO userPasswordDTO, @RequestParam("resetToken") String token){
            MyUser user = userService.getUserByUsername(jwtUtilities.extractUsername(token));
            if(jwtUtilities.isTokenValid(token, user)){
                userService.resetUserPassword(user.getId(), userPasswordDTO);
                return ResponseEntity.ok("Resetováno");
            }
            return ResponseEntity.status(404).body("oj chyba");

    }

    @PostMapping("/sendresetemail")
    public ResponseEntity<?> passwordMail(@RequestParam("username") String username, HttpServletRequest httpRequest) {
        String token = null;
        MyUser user = userService.getUserByUsername(username);
        if (user != null) {
            token = jwtUtilities.generateResetToken(user);

            String appUrl = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort();
            String resetUrl = appUrl + "/api/resetpassword/reset?token=" + token;

            emailService.sendSimpleEmail(user.getEmail(), resetUrl);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(404).build();
    }


}
