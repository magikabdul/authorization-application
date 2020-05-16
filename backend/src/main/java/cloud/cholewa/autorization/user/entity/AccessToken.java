package cloud.cholewa.autorization.user.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "access_tokens")
@Getter
@Setter
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    @Column(length = 1000)
    private String token;
}
