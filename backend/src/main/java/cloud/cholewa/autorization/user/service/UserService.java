package cloud.cholewa.autorization.user.service;

import cloud.cholewa.autorization.exceptions.UserNotFound;
import cloud.cholewa.autorization.user.boundary.AccessTokenRepository;
import cloud.cholewa.autorization.user.boundary.AccessTokenResponse;
import cloud.cholewa.autorization.user.boundary.UserLogin;
import cloud.cholewa.autorization.user.boundary.UserRepository;
import cloud.cholewa.autorization.user.entity.AccessToken;
import cloud.cholewa.autorization.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByToken(String token) {
        return userRepository.findByAccessToken_Token(token);
    }

    public AccessTokenResponse issueToken(UserLogin userLogin) {
        User user = findUserByUsernameOrEmail(userLogin.getLogin());

        checkIsAccountNonExpired(user);
        checkIsAccountNonLocked(user);
        checkIsCredentialNonExpired(user);
        checkIsAccountEnabled(user);

        if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            String token = generateToken(user);
            AccessToken accessToken = getAccessToken(user);

            accessToken.setIssuedAt(LocalDateTime.now());
            accessToken.setExpiresAt(LocalDateTime.now().plusMinutes(10L));
            accessToken.setToken(token);
            accessTokenRepository.save(accessToken);

            user.setAccessToken(accessToken);
            userRepository.save(user);

            return new AccessTokenResponse(token);
        }

        throw new UserNotFound("Invalid credentials");
    }


    private User findUserByUsernameOrEmail(String login) {
        Optional<User> u1 = userRepository.findByUsername(login);
        Optional<User> u2 = userRepository.findByEmail(login);

        if (u1.isPresent() || u2.isPresent()) {
            return u1.orElseGet(u2::get);
        }

        throw new UserNotFound("User not found");
    }

    private void checkIsAccountNonExpired(User user) {
        if (!user.isAccountNonExpired()) {
            throw new UserNotFound("Account is expired");
        }
    }

    private void checkIsAccountNonLocked(User user) {
        if (!user.isAccountNonLocked()) {
            throw new UserNotFound("Account is locked");
        }
    }

    private void checkIsCredentialNonExpired(User user) {
        if (!user.isCredentialsNonExpired()) {
            throw new UserNotFound("Credential has expired");
        }
    }

    private void checkIsAccountEnabled(User user) {
        if (!user.isEnabled()) {
            throw new UserNotFound("Account not active");
        }
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000))
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS512, "secretPa**")
                .compact();
    }

    private AccessToken getAccessToken(User user) {
        try {
            return accessTokenRepository.findById(user.getAccessToken().getId())
                    .orElseGet(AccessToken::new);
        } catch (NullPointerException ignored) {
            return new AccessToken();
        }
    }
}
