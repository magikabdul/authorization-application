package cloud.cholewa.autorization.user.boundary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserCreate {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
