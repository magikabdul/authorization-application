package cloud.cholewa.autorization.user.boundary;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApi {

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserCreate userCreate) {
        return new ResponseEntity<>("Works", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("Works", HttpStatus.OK);
    }
}
