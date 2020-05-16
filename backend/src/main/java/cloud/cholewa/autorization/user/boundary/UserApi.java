package cloud.cholewa.autorization.user.boundary;

import cloud.cholewa.autorization.user.entity.User;
import cloud.cholewa.autorization.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
@CrossOrigin("*")
public class UserApi {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody UserCreate userCreate) {
        return new ResponseEntity<>(userService.addUser(userCreate), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(userService.issueToken(userLogin), HttpStatus.OK);
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
