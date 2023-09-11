package com.zobus.controller;

import com.zobus.pojo.UpdateBusFareInput;
import com.zobus.service.UpdateBusFareServiceProvider;
import jakarta.ws.rs.*;
import java.util.Map;

@Path("admin/bus/fare")
public class UpdateBusFareController {

    @PUT
    public Map<String, Object> updateFare(@HeaderParam("Access-key") String key, UpdateBusFareInput fare){
        return UpdateBusFareServiceProvider.updateBusFare(key, fare);
    }
}
