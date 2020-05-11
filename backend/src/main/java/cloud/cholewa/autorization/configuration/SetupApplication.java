package cloud.cholewa.autorization.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.admin.info")
@Getter
@Setter
public class SetupApplication {

    private String firstName;
    private String lastName;
    private String email;
    private String username;

    private String password;
}
