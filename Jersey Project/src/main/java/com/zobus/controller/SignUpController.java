package com.zobus.controller;

import com.zobus.pojo.SignUpInput;
import com.zobus.service.SignUpServiceProvider;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("sign-up")
public class SignUpController {

    @POST
    @Path("/user")
    public Map<String, Object> createNewResource(@HeaderParam("role") String role, SignUpInput input){
        return SignUpServiceProvider.createNewAccount(role, input);
    }

    @POST
    @Path("/admin")
    public Map<String, Object> createNewAdmin(@HeaderParam("role") String role, SignUpInput input){
        return SignUpServiceProvider.createNewAccount(role, input);
    }
}
