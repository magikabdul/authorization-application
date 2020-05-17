package cloud.cholewa.autorization.user.service;

import cloud.cholewa.autorization.configuration.EmailService;
import cloud.cholewa.autorization.exceptions.UserAccessException;
import cloud.cholewa.autorization.exceptions.UserCreateExceptions;
import cloud.cholewa.autorization.user.boundary.*;
import cloud.cholewa.autorization.user.entity.AccessToken;
import cloud.cholewa.autorization.user.entity.ActivateToken;
import cloud.cholewa.autorization.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final EmailService emailService;
    private final ActivateTokenRepository activateTokenRepository;

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

        throw new UserAccessException("Invalid credentials");
    }

    public UserResponse addUser(UserCreate userCreate, HttpServletRequest httpServletRequest) {
        if(userRepository.findByUsername(userCreate.getUsername()).isPresent()) throw new UserCreateExceptions("Username already exists");
        if(userRepository.findByEmail(userCreate.getEmail()).isPresent()) throw new UserCreateExceptions("Email already exists");

        ActivateToken activateToken = new ActivateToken();
        activateToken.setToken(UUID.randomUUID().toString());
        activateTokenRepository.save(activateToken);

        User newUser = new User();
        newUser.setFirstName(userCreate.getFirstName());
        newUser.setLastName(userCreate.getLastName());
        newUser.setUsername(userCreate.getUsername());
        newUser.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        newUser.setEmail(userCreate.getEmail());
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(false);
        newUser.setRoles("USER");
        newUser.setActivateToken(activateToken);

        User user = userRepository.save(newUser);

        String uri = UriComponentsBuilder.newInstance()
                .scheme(httpServletRequest.getScheme())
                .host("192.168.1.128")
                .port(httpServletRequest.getLocalPort())
                .path("/activate")
                .query("token=" + activateToken.getToken())
                .build()
                .toUriString();

        Thread thread = new Thread(() -> emailService.send(user.getEmail(), "Activate your account", uri));
        thread.start();

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .message("User created")
                .build();
    }

    public UserResponse activateAccount(String token) {
        User user = userRepository.findByActivateToken_Token(token).orElseThrow(()-> new UserCreateExceptions("Activate token invalid"));

        if (!user.isEnabled()) {
            user.setEnabled(true);
            ActivateToken activateToken = user.getActivateToken();
            user.setActivateToken(null);

            userRepository.save(user);
            activateTokenRepository.delete(activateToken);
        } else
            activateTokenRepository.delete(user.getActivateToken());

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .message("Account activated")
                .build();
    }


    private User findUserByUsernameOrEmail(String login) {
        Optional<User> u1 = userRepository.findByUsername(login);
        Optional<User> u2 = userRepository.findByEmail(login);

        if (u1.isPresent() || u2.isPresent()) {
            return u1.orElseGet(u2::get);
        }

        throw new UserAccessException("User not found");
    }

    private void checkIsAccountNonExpired(User user) {
        if (!user.isAccountNonExpired()) {
            throw new UserAccessException("Account is expired");
        }
    }

    private void checkIsAccountNonLocked(User user) {
        if (!user.isAccountNonLocked()) {
            throw new UserAccessException("Account is locked");
        }
    }

    private void checkIsCredentialNonExpired(User user) {
        if (!user.isCredentialsNonExpired()) {
            throw new UserAccessException("Credential has expired");
        }
    }

    private void checkIsAccountEnabled(User user) {
        if (!user.isEnabled()) {
            throw new UserAccessException("Account not active");
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
