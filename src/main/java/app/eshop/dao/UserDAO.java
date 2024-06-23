package app.eshop.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.PanelUI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {
    private final static List<UserDetails> ESHOP_USERS = Arrays.asList(
            new User("Erikos",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User("Eva",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserByUsername(String username){
        return ESHOP_USERS
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Uzivatel nenalezen"));

    }
}
