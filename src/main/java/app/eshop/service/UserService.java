package app.eshop.service;

import app.eshop.dto.RegistrationDTO;
import app.eshop.dto.UserDTO;
import app.eshop.dto.UserPasswordDTO;
import app.eshop.entity.MyUser;

public interface UserService {


    MyUser getUserById(Long id);

    MyUser getUserByUsername(String username);
    MyUser createUser(RegistrationDTO registrationDTO);

    void updateUser(Long id, UserDTO userDTO);

    void updateUserPassword(Long id, UserPasswordDTO userPasswordDTO);
    void resetUserPassword(Long id, UserPasswordDTO userPasswordDTO);

}
