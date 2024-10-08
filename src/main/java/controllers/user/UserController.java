package controllers.user;

import models.user.UserForm;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import services.user.UserService;

@Path("/user")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Transactional
    public Response registerUser(@Valid UserForm data) {
        userService.createUser(data);
        return Response.ok().build();
    }
}
