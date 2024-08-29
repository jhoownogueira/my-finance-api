package repositories;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import models.user.UserForm;
import entity.usuario.Usuario;
import globals.Persistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import services.user.UserService;
import utils.ExceptionUtils;


@ApplicationScoped
public class UserRepository extends Persistence {

    @Inject
    UserService userService;

    public Usuario getUserByUsername(String username) {
        try {
            TypedQuery<Usuario> query = persistence.createQuery("""
                    SELECT u
                    FROM Usuario u
                    WHERE u.username = :username
                    """, Usuario.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            ExceptionUtils.throwBadRequestException("Usuário não encontrado.");
            return null;
        }
    }

    public void createUser(UserForm user) {
        try {
            Usuario newUser = new Usuario();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setSenha(userService.hashPassword(user.getPassword()));
            persistence.persist(newUser);
        } catch (Exception e) {
            Log.error("Erro ao criar usuário.", e);
            ExceptionUtils.throwInternalErrorException("Erro interno no servidor.");
        }

    }

    public boolean usernameExists(String username) {
        TypedQuery<Long> query = persistence.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }

    public boolean emailExists(String email) {
        TypedQuery<Long> query = persistence.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

}
