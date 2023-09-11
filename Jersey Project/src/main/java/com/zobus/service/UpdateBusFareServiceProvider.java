package com.zobus.service;

import com.zobus.database.DatabaseConnectivity;
import com.zobus.pojo.UpdateBusFareInput;
import com.zobus.token.ValidateUser;
import java.sql.*;
import java.util.*;

public class UpdateBusFareServiceProvider {
    public static Map<String, Object> updateBusFare(String key, UpdateBusFareInput fare){
        Map<String, Object> responseMsg = new LinkedHashMap<>();

        // validating token key
        int adminID = ValidateUser.getUserId(key);
        if (adminID > 0){
            try {
                String sqlQuery = "SELECT id FROM seat_type WHERE bus_uid="+fare.getBusUID();
                ResultSet resultSet = DatabaseConnectivity.getDataFromDB(sqlQuery);
                if (resultSet.next()){
                    int busID = resultSet.getInt("id");

                    // after getting bus id, update bus fare in specific bus
                    String query = "UPDATE bus SET fare=" + fare.getFare()+ " WHERE bus="+busID;
                    PreparedStatement pst = DatabaseConnectivity.updateDataToDB(query);
                    pst.executeUpdate();
                    responseMsg.put("Status", "Done");
                    responseMsg.put("Message", "Updated successfully");

                }else {
                    responseMsg.put("Status", "Not updated");
                    responseMsg.put("Message", "Bus UID is not correct");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            responseMsg.put("Status", "Access Denied");
            responseMsg.put("Message", "Unauthorized Access");
        }
        return responseMsg;
    }
}
