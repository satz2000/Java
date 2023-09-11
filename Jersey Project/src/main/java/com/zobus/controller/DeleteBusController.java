package com.zobus.controller;

import com.zobus.pojo.DeleteBusInput;
import com.zobus.service.DeleteBusServiceProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;

@Path("admin/bus/delete")
public class DeleteBusController {

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> deleteTickets(@HeaderParam("Access-key") String key, DeleteBusInput input){
        return DeleteBusServiceProvider.deleteBus(key, input);
    }
}
