package com.zobus.controller;

import com.zobus.pojo.LoginInput;
import com.zobus.service.LoginServiceProvider;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.Map;

@Path("/login")
public class LoginController {

    LoginServiceProvider serviceProvider = new LoginServiceProvider();    // creating obj for service provider which is connected to database

    @GET
    public String helloWorld(){
        return "Welcome to ZOBUS ticket booking app";
    }

    @Path("/admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> adminLogin(@Valid LoginInput loginInput){
        return getStringObjectMap(new LinkedHashMap<>(), serviceProvider.credentialCheck(loginInput));
    }

    @Path("/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> userLogin(LoginInput loginInput){
        return getStringObjectMap(new LinkedHashMap<>(), serviceProvider.credentialCheck(loginInput));
    }

    // calling ViewTicketController class as sub resources, by creating new instance of it
    @Path("user/view-tickets")
    public ViewTicketController viewTickets(){
        return new ViewTicketController();        // creating instance for that class
    }

    // calling AdminBusSummary class as sub resources, by creating new instance of it
    @Path("admin/bus-summary")
    public AdminBusSummary busSummary(){
        return new AdminBusSummary();           // creating instance for that class
    }

    //admin call the bus resource to add new bus here
    @Path("admin/bus")
    public AddBusController addBus(){
        return new AddBusController();            // creating instance for that class
    }

    @Path("admin/bus/delete")
    public DeleteBusController deleteTicket(){
        return new DeleteBusController();
    }

    @Path("/admin/bus/fare")
    public UpdateBusFareController updateFare(){
        return new UpdateBusFareController();
    }
    private Map<String, Object> getStringObjectMap(Map<String, Object> responseMsg, long accessKey) {
        if (accessKey > 0){
            responseMsg.put("Status", "Access Granted");
            responseMsg.put("Access key", String.valueOf(accessKey));
        }else if (accessKey == -1){
            responseMsg.put("Status", "Access Denied");
            responseMsg.put("Error", "Invalid username");
        }else {
            responseMsg.put("Status", "Access Denied");
            responseMsg.put("Error", "Invalid password");
        }
        return responseMsg;
    }
}