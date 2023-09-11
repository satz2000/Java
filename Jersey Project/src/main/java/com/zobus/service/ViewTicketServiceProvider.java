package com.zobus.service;

import com.zobus.token.ValidateUser;
import com.zobus.database.DatabaseConnectivity;
import java.sql.ResultSet;
import java.util.*;

public class ViewTicketServiceProvider {
    private static final Map<String, Object> ticket = new LinkedHashMap<>();
    public static Map<String, Object> ticketDetails(String key){

        int userId = ValidateUser.getUserId(key);
        if (userId > 0) {
            if (!isTicketBookedByUser(userId)) {
                ticket.put("Message", "YOUR NOT BOOKED ANY TICKETS PREVIOUSLY");
            }
        } else {
            ticket.put("Access", "Denied");
            ticket.put("Message", "Unauthorized Access");
        }
        return ticket;
    }

    private static boolean isTicketBookedByUser(int userId){
        boolean isBooked = false;
        // initializing the needed variable for storing the data
        int ticketID, busID, noOfSeatsBooked, noOfSeatsDeleted;
        double fare, cancelledFare;
        String busType, seatType;
        ArrayList<Integer> seatNo = new ArrayList<>();

        ResultSet rs = DatabaseConnectivity.getDataFromDB("SELECT * FROM tickets_detail WHERE user_id=" + userId);
        try {
            while (rs.next()) {          // getting data from ticket details table
                ticketID = rs.getInt("ticket_id");
                busID = rs.getInt("bus");
                noOfSeatsBooked = rs.getInt("no_of_seats_booked");
                noOfSeatsDeleted = rs.getInt("no_of_seats_cancelled");
                fare = rs.getDouble("booked_fare");
                cancelledFare = rs.getDouble("cancelled_fare");

                String query = "SELECT b.bus_type, type " +
                        "FROM bus_type b " +
                        "INNER JOIN seat_type s ON s.bus_type = b.id " +
                        "WHERE s.id=" + busID;
                ResultSet rs1 = DatabaseConnectivity.getDataFromDB(query);

                while (rs1.next()) {         // getting data from buses table
                    busType = rs1.getString("bus_type");
                    seatType = rs1.getString("type");

                    ResultSet rs2 = DatabaseConnectivity.getDataFromDB("SELECT seat_no FROM booked_seat_details WHERE ticket_id='" + ticketID + "'");
                    while (rs2.next()) {     // getting data from seat_details table
                        seatNo.add(rs2.getInt("seat_no"));
                    }
                    ticket.put("Ticket Id", ticketID);
                    ticket.put("Bus", (busType + " " + seatType));
                    ticket.put("No of Seats Booked", noOfSeatsBooked);
                    ticket.put("No of Seats Deleted", noOfSeatsDeleted);
                    ticket.put("Total Fare", (fare + cancelledFare));
                    ticket.put("Fare", fare);
                    ticket.put("Cancellation Fare", cancelledFare);
                    ticket.put("Seat No", seatNo);
                    isBooked = true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isBooked;
    }
}
