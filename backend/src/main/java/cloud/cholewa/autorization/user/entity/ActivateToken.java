package cloud.cholewa.autorization.user.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "activate_tokens")
@Setter
@Getter
public class ActivateToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
}
