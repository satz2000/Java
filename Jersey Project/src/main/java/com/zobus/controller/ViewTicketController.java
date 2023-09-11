package com.zobus.controller;
import com.zobus.service.ViewTicketServiceProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("user/view-tickets")
public class ViewTicketController {        // sub resource of user resource

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getBookedTicketDetails (@HeaderParam("Access-key") String key){
        return ViewTicketServiceProvider.ticketDetails(key);
    }
}
