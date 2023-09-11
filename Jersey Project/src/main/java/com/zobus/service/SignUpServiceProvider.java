package com.zobus.service;

import com.zobus.database.DatabaseConnectivity;
import com.zobus.pojo.SignUpInput;
import java.sql.*;
import java.util.*;

public class SignUpServiceProvider {

    public static Map<String, Object> createNewAccount(String role, SignUpInput input) {
        Map<String, Object> response = new LinkedHashMap<>();
        if(isUsernameNotExists(input, role)){
            try {
                PreparedStatement pst = DatabaseConnectivity.insertDataToDB("INSERT INTO login_credentials(username, password, role) VALUES(?, ?, ?)");
                pst.setString(1, input.getUsername());
                pst.setString(2, input.getPassword());
                pst.setString(3, role);
                pst.executeUpdate();

                // inserting values into user data table
                ResultSet rs = DatabaseConnectivity.getDataFromDB("SELECT * FROM login_credentials WHERE role='" + role + "' AND username='" + input.getUsername() + "'");
                int user_id;
                while (rs.next()) {
                    user_id = rs.getInt("id");

                    PreparedStatement pst1 = DatabaseConnectivity.insertDataToDB("INSERT INTO user_data (user_id, age, gender) VALUES (?, ?, ?)");
                    pst1.setInt(1, user_id);
                    pst1.setInt(2, input.getAge());
                    pst1.setString(3, input.getGender());
                    pst1.executeUpdate();
                    response.put("message", "New Account created successfully");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            response.put("message", "Username already exists! try different one or login with this username");
        }
        return response;
    }

    private static boolean isUsernameNotExists(SignUpInput input, String role) {

        boolean notExists = false;
        String query = "SELECT username FROM login_credentials WHERE username='" + input.getUsername() + "' AND role='" + role + "'";
        ResultSet resultSet = DatabaseConnectivity.getDataFromDB(query);

        try {
            if (!resultSet.next()) {
                notExists = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return notExists;
    }
}
