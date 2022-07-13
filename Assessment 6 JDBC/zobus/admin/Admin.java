package zobus.admin;
import java.sql.ResultSet;
import java.util.*;
import zobus.main.ZoBus;
import zobus.signup.UserSignUpActivity;
import static zobus.main.BusTicketBookingApp.*;
import zobus.database.ConnectingToDB;
public class Admin extends UserSignUpActivity {
	final String ROLE = "ADMIN";
	public void adminLogin(){
		boolean matching = credentialCheck();   // checking login credentials are matching
		if(matching){
			boolean logOut = false;
			while (!logOut) {
				try {
					System.out.println("============================\nADMIN HAS ACCESS TO VIEW\n1. Summary of Bus\n2. Logout");
					sc = new Scanner(System.in);
					int adminChoice = sc.nextInt();
					switch (adminChoice) {
						case 1 -> busSummary();
						case 2 -> {
							logOut = true;
							System.out.println("======================\nLOGOUT SUCCESSFULLY\n======================");
						}
						default -> System.out.println("=================================\nERROR MESSAGE: UNEXPECTED VALUE "+adminChoice+"\n=================================");
					}
				} catch (InputMismatchException inputMismatchException) {
					System.out.println("==================================\nINPUT SHOULD BE AN INTEGER\n==================================");
				}
			}
		}
	}
	private boolean credentialCheck(){
		boolean matching = false;
		System.out.println("=========================\nADMIN LOGIN PORTAL:");
		boolean userFlag = false;
		while (!userFlag) {
			System.out.print("Enter Username: ");
			String username = sc.next();
			ResultSet rs = ConnectingToDB.getDataFromDB("SELECT password FROM login_credentials WHERE role='"+ROLE+"' AND username='"+username+"'");
			try {
				if (rs.next()) {
					boolean passFlag = false;
					while (!passFlag){
						System.out.print("Enter password: ");
						String password = sc.next();
						if(password.equals(rs.getString("password"))){
							matching = true; passFlag = true; userFlag = true;
						}else {
							System.out.println("================================\nERROR MESSAGE: INVALID PASSWORD\n================================");
						}
					}
				}else {
					System.out.println("================================\nERROR MESSAGE: INVALID USERNAME\n================================");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return matching;
	}
	private void busSummary() {
		ResultSet rs = ConnectingToDB.getDataFromDB("SELECT st.id, bt.bus_type, st.type, b.fare, seat_occupied, seat_available " +
				"FROM seat_type st " +
				"INNER JOIN bus_type bt ON st.bus_type = bt.id " +
				"INNER JOIN bus b ON b.bus=st.id " +
				"ORDER BY st.id");
		try {
			while (rs.next()) {
				int busID = rs.getInt("id");
				String busType = rs.getString("bus_type");
				String seatType = rs.getString("type");
				double fare = rs.getDouble("fare");
				int seat_occupied = rs.getInt("seat_occupied");
				int seat_available = rs.getInt("seat_available");
				System.out.println("---------------SUMMARY OF THE BUS---------------");
				System.out.println("BUS ID: "+busID);
				System.out.println("BUS: "+busType+" "+seatType);
				System.out.println("PER TICKET PRICE: "+fare);

				double bookedFare = 0, cancelledFare = 0;
				int noOfSeatsBooked = 0, noOfSeatsCancelled = 0;
				ResultSet rs1 = ConnectingToDB.getDataFromDB("SELECT booked_Fare, cancelled_fare, ticket_id, no_of_seats_booked, no_of_seats_cancelled " +
						"FROM tickets_detail " +
						"WHERE bus="+busID);

				List<Integer> ticketID = new ArrayList<>();
				while (rs1.next()){
					ticketID.add(rs1.getInt("ticket_id"));
					noOfSeatsBooked += rs1.getInt("no_of_seats_booked");
					noOfSeatsCancelled += rs1.getInt("no_of_seats_cancelled");
					bookedFare += rs1.getDouble("booked_fare");
					cancelledFare += rs1.getDouble("cancelled_fare");
				}
				System.out.println("NO OF SEATS BOOKED: "+noOfSeatsBooked);
				System.out.println("NO OF SEATS CANCELLED: "+noOfSeatsCancelled);
				System.out.println("TOTAL FARE COLLECTED: " + (bookedFare+cancelledFare) +
						" ("+noOfSeatsBooked+" SEATS + "+noOfSeatsCancelled+" SEATS CANCELLATION CHARGE "+cancelledFare+")");

				if (ticketID.size()>0) {
					System.out.println("BOOKED SEAT NUMBER WITH PASSENGER NAME:");
					for (int id : ticketID) {
						ResultSet rs2 = ConnectingToDB.getDataFromDB("SELECT seat_no, passenger_name " +
								"FROM booked_seat_details " +
								"WHERE ticket_id=" + id);
						while (rs2.next()) {
							int seatNo = rs2.getInt("seat_no");
							String passenger = rs2.getString("passenger_name");
							System.out.println("[SEAT NO: "+seatNo+", PASSENGER NAME: "+passenger+"]");
						}
					}
				}
				System.out.println("--------------------SEAT VIEW--------------------");
				ZoBus bus = new ZoBus(busID, busType, seatType, seat_available, seat_occupied, fare);
				bus.viewSeatOnlyAdmin();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
