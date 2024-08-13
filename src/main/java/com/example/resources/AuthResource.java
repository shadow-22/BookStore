package com.example.resources;

import com.example.models.UserCredentials;
import com.example.util.JwtUtil;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/auth")
public class AuthResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials credentials) {
        // Replace with your user validation logic
        if ("admin".equals(credentials.getUsername()) && "password".equals(credentials.getPassword())) {
            String token = JwtUtil.generateToken(credentials.getUsername());
            return Response.ok().header("Authorization", "Bearer " + token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
    }
}
