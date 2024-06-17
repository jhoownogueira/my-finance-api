package services.user;

import dto.user.UserDTO;
import dto.user.UserFormDTO;
import entity.Usuario;
import globals.Persistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;
import utils.ExceptionUtils;

@ApplicationScoped
public class UserService extends Persistence {

    public UserDTO validateUser(String username, String password) {
        TypedQuery<Usuario> query = persistence.createQuery("""
                SELECT new dto.user.UserDTO(u)
                FROM Usuario u
                WHERE u.username = :username
                """, Usuario.class);
        query.setParameter("username", username);

        try {
            Usuario user = query.getSingleResult();
            if (checkPassword(password, user.getSenha())) {
                return new UserDTO(user);

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public void createUser(UserFormDTO user) {

        try {
            if (usernameExists(user.getUsername())) {
                ExceptionUtils.throwConflictException("Nome de usuário já existe.");
            }
            if (emailExists(user.getEmail())) {
                ExceptionUtils.throwConflictException("E-mail já existe.");
            }

            Usuario newUser = new Usuario();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setSenha(hashPassword(user.getPassword()));
            persistence.persist(newUser);

        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Erro interno no servidor.");
            System.out.println(e);
            ExceptionUtils.throwInternalErrorException("Erro interno no servidor.");
        }

    }

    private boolean usernameExists(String username) {
        TypedQuery<Long> query = persistence.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }

    private boolean emailExists(String email) {
        TypedQuery<Long> query = persistence.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

}
