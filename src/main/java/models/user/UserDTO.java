package models.user;

import entity.usuario.Usuario;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDTO {

    private UUID id;
    private String username;
    private String email;

    public UserDTO(Usuario user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
