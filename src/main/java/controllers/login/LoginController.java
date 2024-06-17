package controllers.login;

import dto.login.LoginFormDTO;
import dto.login.TokenDTO;
import dto.user.UserDTO;
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
    public Response login(@Valid LoginFormDTO data) {
        UserDTO user = userService.validateUser(data.getUsername(), data.getPassword());
        String token = jwtService.generateToken(user.getUsername(), user.getId(), user.getEmail());
        return Response.ok(new TokenDTO(token)).build();
    }

//    @POST
//    @Path("/refresh-token")
//    public Response refreshToken(@HeaderParam("Authorization") String token) {
//        String newToken = tokenService.refreshToken(token);
//        return Response.ok(newToken).build();
//    }
}
