package cloud.cholewa.autorization.user.boundary;

import cloud.cholewa.autorization.user.entity.User;
import cloud.cholewa.autorization.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
@CrossOrigin("*")
public class UserApi {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody UserCreate userCreate, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(userService.addUser(userCreate, httpServletRequest), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(userService.issueToken(userLogin), HttpStatus.OK);
    }

    @GetMapping("activate")
    public ResponseEntity<UserResponse> activate(@RequestParam String token) {
        return new ResponseEntity<>(userService.activateAccount(token), HttpStatus.ACCEPTED);
    }

    @GetMapping("api/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/user")
    public ResponseEntity<String> userTest(Principal principal) {
        return new ResponseEntity<>("Hello User:" + principal.getName(), HttpStatus.OK);
    }
}
