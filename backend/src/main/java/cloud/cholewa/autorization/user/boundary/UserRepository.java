package cloud.cholewa.autorization.user.boundary;

import cloud.cholewa.autorization.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByAccessToken_Token(String token);

    Optional<User> findByActivateToken_Token(String token);
}
