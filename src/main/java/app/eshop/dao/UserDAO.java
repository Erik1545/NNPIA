package app.eshop.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final PasswordEncoder passwordEncoder;

    private List<UserDetails> eshopUsers() {
        return Arrays.asList(
                new User("Erikos",
                        passwordEncoder.encode("password"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                ),
                new User("Eva",
                        passwordEncoder.encode("heslo"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );
    }

    public UserDetails findUserByUsername(String username) {
        return eshopUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
