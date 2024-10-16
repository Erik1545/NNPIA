package app.eshop.service;

import app.eshop.dto.RegistrationDTO;
import app.eshop.dto.UserDTO;
import app.eshop.dto.UserPasswordDTO;
import app.eshop.entity.MyUser;
import app.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public MyUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public MyUser getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public MyUser createUser(RegistrationDTO registrationDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(registrationDTO.getUsername());
        myUser.setEmail(registrationDTO.getEmail());
        myUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        return userRepository.save(myUser);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        MyUser myUser = userRepository.findById(id).orElse(null);
        myUser.setEmail(userDTO.getEmail());
        userRepository.save(myUser);

    }

    @Override
    public void updateUserPassword(Long id, UserPasswordDTO userPasswordDTO) {
        MyUser myUser = userRepository.findById(id).orElse(null);
        if(passwordEncoder.matches(userPasswordDTO.getSecondPassword(), myUser.getPassword())){
            myUser.setPassword(passwordEncoder.encode(userPasswordDTO.getFirstPassword()));
        }
        userRepository.save(myUser);
    }

    @Override
    public void resetUserPassword(Long id, UserPasswordDTO userPasswordDTO) {
        MyUser myUser = userRepository.findById(id).orElse(null);
        if(userPasswordDTO.getSecondPassword().equals(userPasswordDTO.getFirstPassword())){
            myUser.setPassword(passwordEncoder.encode(userPasswordDTO.getFirstPassword()));
        }
        userRepository.save(myUser);
    }
}
