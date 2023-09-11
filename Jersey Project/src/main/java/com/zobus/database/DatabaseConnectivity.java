package com.zobus.database;

import java.sql.*;

public class DatabaseConnectivity {
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "2000");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet getDataFromDB(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);       // after prepareStatement we need to executeQuery and stored in resultSet
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public static PreparedStatement insertDataToDB(String query){
        try {
            preparedStatement = connection.prepareStatement(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return preparedStatement;
    }

    public static PreparedStatement updateDataToDB(String query){
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return preparedStatement;
    }

    public static PreparedStatement deleteDataFromDB(String query){
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return preparedStatement;
    }
}
