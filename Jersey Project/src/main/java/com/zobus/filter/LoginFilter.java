package com.zobus.filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.util.*;

@Provider
@PreMatching
public class LoginFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Access-key";
    private static final List<String> SECURED_URL_PREFIX = Arrays.asList("login/admin", "login/user");

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (SECURED_URL_PREFIX.contains(requestContext.getUriInfo().getPath())){
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader !=  null){
                Response unauthorizedStatus = Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity("Unwanted header for this resource")
                        .build();
                requestContext.abortWith(unauthorizedStatus);
            }
        }
    }
}
