package app.eshop.controller;

import app.eshop.dto.UserDTO;
import app.eshop.dto.UserPasswordDTO;
import app.eshop.entity.MyUser;
import app.eshop.security.JWTUtilities;
import app.eshop.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtilities jwtUtilities;


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        MyUser myUser = userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(myUser.getEmail());
        return userDTO;
    }

    @PutMapping("/{id}")
    public UserDTO updateUserById(@PathVariable Long id, @RequestParam("email") @NotNull String email){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userService.updateUser(id, userDTO);
        return userDTO;
    }

    @PutMapping("/password/{id}")
    public void updateUserPasswordById(@PathVariable Long id, @RequestParam("oldPassword") @NotNull String oldPassword, @RequestParam("newPassword") @NotNull String newPassword){
        UserPasswordDTO userPasswordDTO = new UserPasswordDTO();
        userPasswordDTO.setSecondPassword(oldPassword);
        userPasswordDTO.setFirstPassword(newPassword);

        userService.updateUserPassword(id, userPasswordDTO);
    }


}
