package cloud.cholewa.autorization.user.boundary;

import cloud.cholewa.autorization.user.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {


}
