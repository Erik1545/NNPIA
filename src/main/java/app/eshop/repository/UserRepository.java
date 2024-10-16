package app.eshop.repository;

import app.eshop.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {


    MyUser findUserByUsername(String username);

    MyUser findUserByEmail(String email);
}
