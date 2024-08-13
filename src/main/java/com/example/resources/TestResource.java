package com.example.resources;

import com.example.filters.JWTRequired;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
    @GET
    @JWTRequired
    @Produces(MediaType.TEXT_PLAIN) // This overrides the class-level @Produces
    public String testJWT() {
        return "JWT is valid. Test successful!";
    }

    /*
    @GET
    @Path("/another")
    public Response anotherEndpoint() {
        // This will return JSON since it uses the class-level @Produces
        return Response.ok(new MyObject()).build();
    }
    */
}
