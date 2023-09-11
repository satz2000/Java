package com.zobus.controller;

import com.zobus.pojo.AddBusInput;
import com.zobus.service.AddBusServiceProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("admin/bus")
public class AddBusController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> addNewBus(@HeaderParam("Access-key") String key, AddBusInput input){
        return AddBusServiceProvider.addBus(key, input);
    }
}
