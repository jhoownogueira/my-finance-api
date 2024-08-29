package controllers.login;

import models.login.LoginForm;
import models.login.TokenDTO;
import models.user.UserDTO;
import globals.JwtService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import services.user.UserService;

@Path("/auth")
public class LoginController {

    @Inject
    UserService userService;


    @Inject
    JwtService jwtService;

    @POST
    @Transactional
    @Path("/login")
    public Response login(@Valid LoginForm data) {
        UserDTO user = userService.validateUser(data);
        String token = jwtService.generateToken(user.getUsername(), user.getId(), user.getEmail());
        return Response.ok(new TokenDTO(token)).build();
    }
}
