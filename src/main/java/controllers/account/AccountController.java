package controllers.account;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
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

    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateAccount(@PathParam UUID id, @Valid AccountForm data) {
        accountService.updateAccount(id, data);
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteAccount(@PathParam UUID id) {
        accountService.deleteAccount(id);
        return Response.ok().build();
    }
}
