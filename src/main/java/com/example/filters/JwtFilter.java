package com.example.filters;

import com.example.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@JWTRequired
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final SecretKey SECRET_KEY = JwtUtil.getSecretKey();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (resourceInfo.getResourceMethod() != null &&
            resourceInfo.getResourceMethod().isAnnotationPresent(JWTRequired.class) ||
            resourceInfo.getResourceClass().isAnnotationPresent(JWTRequired.class)) {
            // Get the Authorization header from the request
            String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);

            // If no authorization header or it doesn't start with "Bearer ", abort the request
            if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }

            // Extract the token from the header
            String token = authorizationHeader.substring(BEARER_PREFIX.length());

            try {
                // Validate the token
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Add claims to the request context if you need them later
                requestContext.setProperty("claims", claims);

            } catch (Exception e) {
                // If the token is invalid, abort the request
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
