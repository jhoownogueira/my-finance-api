package utils;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ExceptionUtils {

    public static void throwConflictException(String message) {
        Response response = Response
                .status(Response.Status.CONFLICT)
                .entity(message)
                .build();
        throw new WebApplicationException(response);
    }

    public static void throwBadRequestException(String message) {
        Response response = Response
                .status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build();
        throw new WebApplicationException(response);
    }

    public static void throwInternalErrorException(String message) {
        Response response = Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message)
                .build();
        throw new WebApplicationException(response);
    }


}