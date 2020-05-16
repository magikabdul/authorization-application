package cloud.cholewa.autorization.user.boundary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserLogin {

    private String login;
    private String password;
}
