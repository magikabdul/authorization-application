package cloud.cholewa.autorization.configuration;

import cloud.cholewa.autorization.user.boundary.UserRepository;
import cloud.cholewa.autorization.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsApplication implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> oUserByName = userRepository.findByUsername(s);
        Optional<User> oUserByEmail = userRepository.findByEmail(s);

        if (oUserByName.isPresent()) {
            return oUserByName.get();
        } else if (oUserByEmail.isPresent()) {
            return oUserByEmail.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
