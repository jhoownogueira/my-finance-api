package dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginFormDTO {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;
}
