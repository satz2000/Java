package com.zobus.filter;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.util.*;

@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_KEY = "Access-key";
    private static final List<String> AUTHENTICATED_URL = Arrays.asList("login/user/view-tickets", "login/admin/bus-summary", "login/user/cancel-ticket", "login/admin/bus", "login/admin/bus/fare", "login/admin/bus/delete");

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (AUTHENTICATED_URL.contains(requestContext.getUriInfo().getPath())){
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (!(authHeader != null && authHeader.size()>0)){
                Response authResponseStatus = Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity("User cannot access the resource")
                                                        .build();
                requestContext.abortWith(authResponseStatus);
            }
        }
    }
}
