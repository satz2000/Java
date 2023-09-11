package com.zobus.service;

import com.zobus.token.TokenMapWithId;
import com.zobus.database.DatabaseConnectivity;
import com.zobus.pojo.LoginInput;
import com.zobus.randomkey.TokenKeyGenerator;
import java.sql.ResultSet;

public class LoginServiceProvider {



    String password;
    String role;
    public long credentialCheck(LoginInput loginInput) {
        int id = 0;
        long accessKey = 0;

        try {
            if (isUserExists(loginInput)) {
                String sqlQuery = "SELECT id FROM login_credentials WHERE username='" + loginInput.getUsername() + "' AND password='" + loginInput.getPassword() + "' AND role='" + role + "'";
                ResultSet resultSet = DatabaseConnectivity.getDataFromDB(sqlQuery);
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                } else {
                    id = -2;  // if password is wrong
                }
            } else {
                // if username is wrong
                id = -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (id > 0) {
            accessKey = Long.parseLong(TokenKeyGenerator.generateToken());
            TokenMapWithId.mappingKeyWithId(accessKey, id);            // mapping the access key with admin user id
        }
        return accessKey;
    }

    private boolean isUserExists(LoginInput loginInput) {
        boolean isExists = false;
        try {
            String sql = "SELECT password, role FROM login_credentials WHERE username='" + loginInput.getUsername() + "'";
            ResultSet rs = DatabaseConnectivity.getDataFromDB(sql);
            while (rs.next()){
                password = rs.getString("password");
                role = rs.getString("role");
                isExists = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isExists;
    }
}
