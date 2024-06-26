package app.eshop.security;
import app.eshop.dao.UserDAO;
import app.eshop.dto.AuthRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JWTUtilities jwtUtilities;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        final UserDetails userDetails = userDAO.findUserByUsername(authRequestDTO.getUsername());
        if(userDetails != null){
            return ResponseEntity.ok(jwtUtilities.generateToken(userDetails));
        }
        return ResponseEntity.status(400).body("Nastala chyba");
    }
}
