package cloud.cholewa.autorization.configuration;

import cloud.cholewa.autorization.user.boundary.UserRepository;
import cloud.cholewa.autorization.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final UserRepository userRepository;
    private final SetupApplication setupApplication;


    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void addDefaultAdmin() {
        if (userRepository.findByUsername(setupApplication.getUsername()).isEmpty()) {
            User admin = new User();
            admin.setFirstName(setupApplication.getFirstName());
            admin.setLastName(setupApplication.getLastName());
            admin.setUsername(setupApplication.getUsername());
            admin.setEmail(setupApplication.getEmail());
            admin.setPassword(getPasswordEncoder().encode(setupApplication.getPassword()));
            admin.setCredentialsNonExpired(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setEnabled(true);
            admin.setRoles("ADMIN");

            userRepository.save(admin);
        }
    }
}
