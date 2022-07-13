package zobus.login;
import java.sql.*;
import java.util.*;
import zobus.database.ConnectingToDB;
import zobus.main.ZoBus;
import static zobus.main.BusTicketBookingApp.*;
public class CancelBookedTickets {
	String busType = null;
	String seatType = null;
	int busID = 0;
	int ticketID = 0;
	int noOfTicketsBooked = 0;
	double totalAmount = 0;
	boolean cancellationCondition = true;
	double b_fare = 0;
	int b_availableSeat = 0;
	int b_bookedSeat = 0;
	ArrayList<Integer> bookedSeatNo = new ArrayList<>();
	public void cancelTickets(int customer_id){
		boolean isTicketBooked = printTicketDetails(customer_id);
		if(isTicketBooked && bookedSeatNo.size()>0){
			boolean cancelFlag = false;
			while (!cancelFlag){
				try {
					System.out.print("1. Cancel all\n2. Delete partially\n");
					sc = new Scanner(System.in);
					int choice = sc.nextInt();
					switch (choice) {
						case 1 -> {
							removingBookedTickets(choice);
							System.out.println("===================================\nSUCCESSFULLY CANCELLED ALL TICKETS\n===================================");
							cancelFlag = true;
						}
						case 2 -> {
							removingBookedTickets(choice);
							System.out.println("===================================\nSELECTED TICKETS ARE CANCELLED\n===================================");
							cancelFlag = true;
						}
						default -> System.out.println("===============================\nERROR MESSAGE: UNEXPECTED VALUE: " + choice + "\n===============================");
					}
				} catch (Exception e) {
					System.out.println("===============================\nINPUT SHOULD BE AN INTEGER\n===============================");
				}
			}
		}
	}
	private boolean printTicketDetails(int customer_id) {
		boolean match = false, exist = true;
		ResultSet resultSet = ConnectingToDB.getDataFromDB("SELECT * FROM tickets_detail WHERE user_id="+customer_id);
		try {
			while (resultSet.next()) {          // getting ticket details data
				ticketID = resultSet.getInt("ticket_id");
				busID = resultSet.getInt("bus");
				noOfTicketsBooked = resultSet.getInt("no_of_seats_booked");
				totalAmount = resultSet.getDouble("booked_fare");

				String query = "SELECT b.bus_type, type, seat_available, seat_occupied, fare " +
						"FROM seat_type st " +
						"INNER JOIN bus ON bus.bus=st.id " +
						"INNER JOIN bus_type b ON b.id=st.bus_type " +
						"WHERE bus.bus ="+busID;
				ResultSet rs1 = ConnectingToDB.getDataFromDB(query);
				while (rs1.next()){             // getting bus details data
					busType = rs1.getString("bus_type");
					seatType = rs1.getString("type");
					b_availableSeat = rs1.getInt("seat_available");
					b_bookedSeat = rs1.getInt("seat_occupied");
					b_fare = rs1.getDouble("fare");

					ResultSet rs2 = ConnectingToDB.getDataFromDB("SELECT seat_no FROM booked_seat_details WHERE ticket_id='"+ticketID+"'");
					while (rs2.next()){         // getting booked seat details data
						bookedSeatNo.add(rs2.getInt("seat_no"));
					}

					System.out.println("===========================");      // printing the information about your tickets detail
					System.out.println("TICKET DETAILS ARE:");
					System.out.println("customer id: "+customer_id);
					System.out.println("Bus type: "+busType);
					System.out.println("Seat type: "+seatType);
					System.out.println("No of tickets: "+ noOfTicketsBooked);
					System.out.println("Booked seats: "+ bookedSeatNo);
					System.out.println("===========================");
					match = true; exist = false;
				}
			}
			if (exist) {
				System.out.println("======================================\nYOUR NOT BOOKED ANY TICKETS PREVIOUSLY\n======================================");
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return match;
	}
	private void removingBookedTickets(int choice) {
		ZoBus bus = new ZoBus(busID, busType, seatType, b_availableSeat, b_bookedSeat, b_fare);
		ArrayList<Integer> deletedSeats = bus.deleteTickets(choice, bookedSeatNo);
		double cancellationFare = bus.calculateFare(cancellationCondition, deletedSeats.size());
		int noOfTicketDeleted = deletedSeats.size();
		int noOfTicketBooked = noOfTicketsBooked-noOfTicketDeleted;
		double fare = bus.calculateFare(false, noOfTicketBooked);

		try {
			PreparedStatement pst = ConnectingToDB.updateDataToDB("UPDATE tickets_detail SET no_of_seats_booked=" + noOfTicketBooked + ", no_of_seats_cancelled=" +
					noOfTicketDeleted + ", booked_fare=" + fare + ", cancelled_fare=" + cancellationFare + " WHERE ticket_id=" + ticketID);
			pst.executeUpdate();

			for(int seat: deletedSeats) {
				PreparedStatement seatDetailDelete = ConnectingToDB.deleteDataFromDB("DELETE FROM booked_seat_details WHERE seat_no=" +seat+" AND ticket_id="+ticketID);
				seatDetailDelete.executeUpdate();
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}


	}
}
