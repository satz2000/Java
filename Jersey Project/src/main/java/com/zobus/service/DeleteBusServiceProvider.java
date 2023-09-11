package com.zobus.service;

import com.zobus.database.DatabaseConnectivity;
import com.zobus.pojo.DeleteBusInput;
import com.zobus.token.ValidateUser;
import java.sql.*;
import java.util.*;

public class DeleteBusServiceProvider {

    public static Map<String, Object> deleteBus(String key, DeleteBusInput input){
        Map<String, Object> responseMsg = new LinkedHashMap<>();

        int adminID = ValidateUser.getUserId(key);
        if (adminID > 0){
            String sqlQuery = "SELECT id FROM seat_type WHERE bus_uid="+input.getBusUID();
            ResultSet rs = DatabaseConnectivity.getDataFromDB(sqlQuery);
            try {
                if (rs.next()) {
                    int busId = rs.getInt("id");

                    // performing delete operation in bus table where bus = user selected one
                    String query = "DELETE FROM bus WHERE bus="+busId;
                    PreparedStatement pst = DatabaseConnectivity.deleteDataFromDB(query);
                    pst.executeUpdate();

                    // also delete the bus from seat type table where it's a parent for bus table
                    String query1 = "DELETE FROM seat_type WHERE bus_uid="+input.getBusUID();
                    PreparedStatement pst1 = DatabaseConnectivity.deleteDataFromDB(query1);
                    pst1.executeUpdate();

                    responseMsg.put("Status", "Done");
                    responseMsg.put("Message", "Successfully Deleted");
                } else {
                    responseMsg.put("Status", "Not Deleted");
                    responseMsg.put("Message", "Invalid bus UID");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            responseMsg.put("Status","Access Denied");
            responseMsg.put("Message", "Unauthorized Access");
        }
        return responseMsg;
    }
}
