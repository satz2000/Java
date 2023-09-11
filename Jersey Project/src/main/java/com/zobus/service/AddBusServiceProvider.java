package com.zobus.service;

import com.zobus.database.DatabaseConnectivity;
import com.zobus.pojo.AddBusInput;
import com.zobus.token.ValidateUser;
import java.sql.*;
import java.util.*;

public class AddBusServiceProvider {

    static Map<String, Object> responseMsg = new LinkedHashMap<>();
    public static Map<String, Object> addBus(String key, AddBusInput input) {

        if (busUidNotExists(key, input)){
            // get id from bus_type table for adding new bus in bus table
            String sqlQuery = "SELECT id FROM bus_type WHERE bus_type='" + input.getBusType() + "'";
            ResultSet resultSet = DatabaseConnectivity.getDataFromDB(sqlQuery);

            try {
                if (resultSet.next()) {
                    int busTypeID = resultSet.getInt("id");

                    // insert seat type and bus_type (bus_type table pk)
                    String query = "INSERT INTO seat_type(bus_type, type, bus_uid) VALUES(?, ?, ?)";
                    PreparedStatement pst = DatabaseConnectivity.insertDataToDB(query);
                    pst.setInt(1, busTypeID);
                    pst.setString(2, input.getSeatType());
                    pst.setInt(3, input.getBusUID());
                    pst.executeUpdate();

                    // And get id(pk) from seat_type table
                    String query1 = "SELECT id FROM seat_type WHERE bus_type=" + busTypeID + " AND type='" + input.getSeatType() + "' AND bus_uid=" + input.getBusUID();
                    ResultSet resultSet1 = DatabaseConnectivity.getDataFromDB(query1);

                    if (resultSet1.next()) {
                        int busID = resultSet1.getInt("id");
                        System.out.println(busID);

                        // then finally insert the other data into bus table
                        String query3 = "INSERT INTO bus(bus, seat_capacity, seat_available, seat_occupied, fare) VALUES(?, ?, ?, ?, ?)";
                        PreparedStatement pst1 = DatabaseConnectivity.insertDataToDB(query3);
                        pst1.setInt(1, busID);
                        pst1.setInt(2, input.getSeatCapacity());
                        pst1.setInt(3, input.getSeatCapacity());
                        pst1.setInt(4, 0);
                        pst1.setDouble(5, input.getFare());
                        pst1.executeUpdate();
                        responseMsg.put("Status", "Ok");
                        responseMsg.put("Message", "Added Successfully");
                    }
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        } else {
            responseMsg.put("Status", "Not updatable");
            responseMsg.put("Message", "Bus UID should be unique for every bus");
        }
        return responseMsg;
    }
    private static boolean isValidUser(String key){
        boolean keyExists = false;
        int adminId = ValidateUser.getUserId(key);

        if (adminId > 0) {
            keyExists = true;
        }
        return keyExists;
    }

    private static boolean busUidNotExists(String key, AddBusInput input){
        boolean isNotExists = false;

        if (isValidUser(key)) {
            String SQuery = "SELECT * FROM seat_type WHERE bus_uid=" + input.getBusUID();
            ResultSet bus_UID = DatabaseConnectivity.getDataFromDB(SQuery);

            try {
                if (!bus_UID.next()) {
                    isNotExists = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            responseMsg.put("Status", "Access Denied");
            responseMsg.put("Message", "Unauthorized Access");
        }
        return isNotExists;
    }
}
