package services.user;

import entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.login.LoginFormDTO;
import models.user.UserDTO;
import models.user.UserFormDTO;
import repository.user.UserRepository;
import utils.ExceptionUtils;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public void createUser(UserFormDTO user) {

        if (userRepository.usernameExists(user.getUsername())) {
            ExceptionUtils.throwConflictException("Nome de usuário já existe.");
            return;
        }
        if (userRepository.emailExists(user.getEmail())) {
            ExceptionUtils.throwConflictException("E-mail já existe.");
            return;
        }

        userRepository.createUser(user);
    }

    public UserDTO validateUser(LoginFormDTO data) {
        Usuario user = userRepository.getUserByUsername(data.getUsername());

        if (user == null) {
            ExceptionUtils.throwInternalErrorException("Usuário não encontrado.");
            return null;
        }

        if (checkPassword(data.getPassword(), user.getSenha())) {
            return new UserDTO(user);

        } else {
            ExceptionUtils.throwBadRequestException("Senha inválida.");
            return null;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
