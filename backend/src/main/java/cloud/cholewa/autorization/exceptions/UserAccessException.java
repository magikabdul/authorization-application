package cloud.cholewa.autorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserAccessException extends RuntimeException{
    public UserAccessException(String message) {
        super(message);
    }
}
