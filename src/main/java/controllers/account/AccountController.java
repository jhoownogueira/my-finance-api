package controllers.account;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import models.account.AccountForm;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import services.account.AccountService;

import java.util.UUID;

@Path("/account")
public class AccountController {

    @Inject
    AccountService accountService;

    @POST
    @Transactional
    public Response createAccount(@Valid AccountForm data) {
        accountService.createAccount(data);
        return Response.ok().build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getAccountsByUserId(@PathParam UUID userId) {
        return Response.ok(accountService.getAccountsByUserId(userId)).build();
    }

    @GET
    @Path("/{id}")
    public Response getAccountById(@PathParam UUID id) {
        return Response.ok(accountService.getAccountById(id)).build();
    }
}
