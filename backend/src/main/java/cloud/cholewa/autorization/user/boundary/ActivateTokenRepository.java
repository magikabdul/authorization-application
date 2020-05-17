package cloud.cholewa.autorization.user.boundary;

import cloud.cholewa.autorization.user.entity.ActivateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivateTokenRepository extends JpaRepository<ActivateToken, Long> {
}
