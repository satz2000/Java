package com.zobus.controller;
import com.zobus.service.AdminSummaryServiceProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("admin/bus-summary")
public class AdminBusSummary {      // sub resource of admin resource

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> busSummary(@HeaderParam("Access-key") String key){
        return AdminSummaryServiceProvider.getAllBusSummary(key);
    }
}
