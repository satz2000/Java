package com.zobus.service;

import com.zobus.token.ValidateUser;
import com.zobus.database.DatabaseConnectivity;
import java.sql.ResultSet;
import java.util.*;

public class AdminSummaryServiceProvider {
    public static List<Map<String, Object>> getAllBusSummary(String key) {
        Map<String, Object> summary;
        List<Map<String, Object>> lisOfSummary = new ArrayList<>();
        int admin_id = ValidateUser.getUserId(key);
        if (admin_id > 0){
            ResultSet rs = DatabaseConnectivity.getDataFromDB("SELECT st.id, bt.bus_type, st.type, b.fare, seat_occupied, seat_available " +
                    "FROM seat_type st " +
                    "INNER JOIN bus_type bt ON st.bus_type = bt.id " +
                    "INNER JOIN bus b ON b.bus=st.id " +
                    "ORDER BY st.id");
            try {
                while (rs.next()) {
                    summary = new LinkedHashMap<>();
                    int busID = rs.getInt("id");
                    String busType = rs.getString("bus_type");
                    String seatType = rs.getString("type");
                    double fare = rs.getDouble("fare");

                    double bookedFare = 0, cancelledFare = 0;
                    int noOfSeatsBooked = 0, noOfSeatsCancelled = 0;
                    ArrayList<Integer> ticketID = new ArrayList<>();
                    ResultSet rs1 = DatabaseConnectivity.getDataFromDB("SELECT booked_Fare, cancelled_fare, ticket_id, no_of_seats_booked, no_of_seats_cancelled " +
                                                                                "FROM tickets_detail " +
                                                                                "WHERE bus="+busID);
                    while (rs1.next()){
                        ticketID.add(rs1.getInt("ticket_id"));
                        noOfSeatsBooked += rs1.getInt("no_of_seats_booked");
                        noOfSeatsCancelled += rs1.getInt("no_of_seats_cancelled");
                        bookedFare += rs1.getDouble("booked_fare");
                        cancelledFare += rs1.getDouble("cancelled_fare");
                    }

                    Map<Integer, String> passenger_details = new LinkedHashMap<>();
                    if (ticketID.size()>0) {
                        for (int id : ticketID) {
                            ResultSet rs2 = DatabaseConnectivity.getDataFromDB("SELECT seat_no, passenger_name " +
                                    "FROM booked_seat_details " +
                                    "WHERE ticket_id=" + id);
                            while (rs2.next()) {
                                passenger_details.put(rs2.getInt("seat_no"), rs2.getString("passenger_name"));
                            }
                        }
                    }
                    summary.put("Bus", (busType+" "+seatType));
                    summary.put("Ticket Price", fare);
                    summary.put("No of seats occupied", noOfSeatsBooked);
                    summary.put("No of seats cancelled", noOfSeatsCancelled);
                    summary.put("Total fare collected", (bookedFare+cancelledFare));
                    summary.put("Booked seats fare", bookedFare);
                    summary.put("Cancelled seats fare", cancelledFare);
                    if (!passenger_details.isEmpty()) {
                        summary.put("Passenger details", passenger_details);
                    }
                    lisOfSummary.add(summary);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            summary = new LinkedHashMap<>();
            summary.put("Access", "Denied");
            summary.put("Message", "Unauthorized Access");
            lisOfSummary.add(summary);
        }
        return lisOfSummary;
    }
}
