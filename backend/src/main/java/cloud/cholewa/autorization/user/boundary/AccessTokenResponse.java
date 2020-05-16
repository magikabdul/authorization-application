package cloud.cholewa.autorization.user.boundary;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AccessTokenResponse {
    @NonNull
    private String token;
}
