package zobus.login;
import java.sql.ResultSet;
import java.util.ArrayList;
import zobus.database.ConnectingToDB;
public class ViewBookedTickets {
	public void viewTickets(int customerID) {
		int ticketID;
		String busType;
		String seatType;
		int busID;
		int noOfTicketsBooked;
		ArrayList<Integer> seatNo = new ArrayList<>();
		int noOfTicketsDeleted;
		double fare;
		double cancelledFare;

		ResultSet rs = ConnectingToDB.getDataFromDB("SELECT * FROM tickets_detail WHERE user_id="+customerID);
		try {
			boolean exists = true;
			while (rs.next()){          // getting data from ticket details table
				ticketID = rs.getInt("ticket_id");
				busID = rs.getInt("bus");
				noOfTicketsBooked = rs.getInt("no_of_seats_booked");
				noOfTicketsDeleted = rs.getInt("no_of_seats_cancelled");
				fare = rs.getDouble("booked_fare");
				cancelledFare = rs.getDouble("cancelled_fare");

				String query = "SELECT b.bus_type, type " +
						"FROM bus_type b " +
						"INNER JOIN seat_type s ON s.bus_type = b.id " +
						"WHERE s.id="+busID;
				ResultSet rs1 = ConnectingToDB.getDataFromDB(query);
				while (rs1.next()){         // getting data from buses table
					busType = rs1.getString("bus_type");
					seatType = rs1.getString("type");

					ResultSet rs2 = ConnectingToDB.getDataFromDB("SELECT seat_no FROM booked_seat_details WHERE ticket_id='"+ticketID+"'");
					while (rs2.next()){     // getting data from seat_details table
						seatNo.add(rs2.getInt("seat_no"));
					}
					System.out.println("============================\nTICKET DETAILS ARE:");         // printing all the information
					System.out.println("Ticket ID: " + ticketID);
					System.out.println("Bus Type: " + busType);
					System.out.println("Seat Type: " + seatType);
					System.out.println("No.of Tickets Booked: " + noOfTicketsBooked);
					System.out.println("Seat No: " + seatNo);
					System.out.println("No.of Tickets Cancelled: " + noOfTicketsDeleted);
					System.out.println("Booking Fare: " + fare);
					System.out.println("Cancellation Fare: "+cancelledFare);
					System.out.println("Customer ID: " + customerID);
					System.out.println("============================");
					exists = false;             // if tickets already booked by this customer, make the exists = false
				}
			}
			if (exists){
				System.out.println("=======================================\nYOUR NOT BOOKED ANY TICKETS PREVIOUSLY\n=======================================");
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}