package models.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserFormDTO {

        @NotNull
        @NotBlank(message = "Username is required")
        @Size(min = 4 , max = 50, message = "Username must be between 3 and 50 characters")
        private String username;

        @NotNull
        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        private String email;

        @NotNull
        @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
        @NotBlank(message = "Password is required")
        private String password;

        public UserFormDTO(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
}
