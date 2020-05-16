package cloud.cholewa.autorization.user.boundary;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class UserResponse {
    private final Long id;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;

    private final String message;
}
