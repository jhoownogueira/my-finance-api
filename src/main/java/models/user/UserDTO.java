package models.user;

import entity.Usuario;
import lombok.Getter;

@Getter
public class UserDTO {

    private Integer id;
    private String username;
    private String email;

    public UserDTO(Usuario user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
