package zobus.login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import static zobus.main.BusTicketBookingApp.*;
import zobus.database.ConnectingToDB;
import zobus.main.ZoBus;

public class UserLoginActivity {
	int customerID = 0;
	public void userLogin() {
		boolean matching = credentialCheck();
		if(matching){
			boolean mainMenuFlag = false;
			while (!mainMenuFlag) {
				try {
					System.out.println("-------- HOME PAGE --------\n1. BOOK TICKETS\n2. VIEW BOOKED TICKETS\n3. CANCEL TICKETS\n4. LOGOUT");
					sc = new Scanner(System.in);
					int userChoice = sc.nextInt();
					switch (userChoice) {
						case 1 -> {
							ResultSet rs_1 = ConnectingToDB.getDataFromDB("SELECT user_id FROM tickets_detail WHERE user_id='" +customerID+ "'");
							if (rs_1.next()) {
								System.out.println("=======================================\nONE CUSTOMER CAN BOOK ONE TICKETS ONLY\n=======================================");
							} else {
								busSelection();// call bus selection method
							}
						}
						case 2 -> new ViewBookedTickets().viewTickets(customerID);
						case 3 -> new CancelBookedTickets().cancelTickets(customerID);
						case 4 -> {mainMenuFlag = true;
							System.out.println("==========================\nLOGOUT SUCCESSFULLY\n==========================");
						}default -> System.out.println("===============================\nERROR MESSAGE: UNEXPECTED VALUE: " + userChoice + "\n===============================");
					}
				} catch (Exception e){System.out.println("===========================\nINPUT SHOULD BE AN INTEGER\n===========================");}
			}
		}
	}
	private Boolean credentialCheck(){
		boolean matching = false;
		System.out.println("===================\nUSER LOGIN PORTAL:\n===================");
		boolean userFlag = false;
		while (!userFlag) {
			System.out.print("Enter username: ");
			String username = sc.next();
			ResultSet rs = ConnectingToDB.getDataFromDB("SELECT * FROM login_credentials WHERE role='USER' AND username='" + username + "'");
			try {
				if(rs.next()){
					boolean passFlag = false;
					while (!passFlag) {
						System.out.print("Enter password: ");
						String password = sc.next();
						ResultSet resultSet = ConnectingToDB.getDataFromDB("SELECT id FROM login_credentials WHERE username='"+username+"' AND password='" + password + "'");
						if (!resultSet.next()){
							System.out.println("===============================\nERROR MESSAGE: INVALID PASSWORD\n===============================");
						}else {
							customerID = resultSet.getInt("id");
							passFlag = true; userFlag = true; matching = true;
							System.out.println("==========================\nLOGIN SUCCESSFULLY!!!\n==========================");
						}
					}
				} else {System.out.println("===============================\nERROR MESSAGE: INVALID USERNAME\n===============================");}
			} catch (Exception e) {System.out.println(e.getMessage());}
		}
		return matching;
	}
	private void busSelection(){
		boolean condition = false;
		while (!condition){
			try {
				System.out.println("============================\nCHOOSE BUS TYPE:\n1. AC\n2. NON AC\n3. BOTH");
				int choice = sc.nextInt();
				switch (choice){
					case 1 -> {
						singleCondition(choice, "AC");
						condition = true;
					}
					case 2 -> {
						singleCondition(choice, "NON AC");
						condition = true;
					}
					case 3 -> {
						bothCondition();
						condition = true;
					}
					default -> System.out.println("=================================\nERROR MESSAGE: UNEXPECTED VALUE\n=================================");
				}
			}catch (Exception e){
				sc = new Scanner(System.in);
				System.out.println("=================================\nINPUT SHOULD BE AN INTEGER\n=================================");
			}
		}
	}
	private void singleCondition(int bus_id, String bus){
		boolean condition = false;
		while (!condition){
			String query = "SELECT s.id, type " +
					"FROM bus_type b " +
					"JOIN seat_type s ON s.bus_type="+bus_id+" AND b.bus_type='"+bus+"'";
			ResultSet rs = ConnectingToDB.getDataFromDB(query);
			Map<Integer, Integer> busKeyMapping = new LinkedHashMap<>();
			try {
				int index = 1;
				System.out.println("=======================\nCHOOSE SEAT TYPE:");
				while (rs.next()){      // print seat type with ordered index number
					int id = rs.getInt("id");
					String seatType = rs.getString("type");
					System.out.println(index+". "+ bus+" "+seatType);
					busKeyMapping.put(index, id);
					index++;
				}
				String query1 = "SELECT s.id, b.bus_type, type " +
						"FROM bus_type b " +
						"JOIN seat_type s ON s.bus_type="+bus_id+" AND b.bus_type='"+bus+"'";
				ResultSet rs1 = ConnectingToDB.getDataFromDB(query1);
				Map<Integer, List<String>> busMapping = new LinkedHashMap<>();
				while (rs1.next()){
					int id = rs1.getInt("id");
					String busType = rs1.getString("bus_type");
					String seatType = rs1.getString("type");
					busMapping.put(id, Arrays.asList(busType, seatType));
				}
				try {
					int choice = sc.nextInt();
					if(choice > 0 && choice <=2) {
						int busID = busKeyMapping.get(choice);
						List<String> bus_type = busMapping.get(busID);
						newTicketObjectCreate(busID, bus_type.get(0), bus_type.get(1));
						condition = true;
					} else{
						System.out.println("==================================\nERROR MESSAGE: UNEXPECTED VALUE\n==================================");
					}
				} catch (Exception e){
					sc = new Scanner(System.in);
					System.out.println("==================================\nINPUT SHOULD BE AN INTEGER\n==================================");
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	private void bothCondition(){
		boolean condition = false;
		while (!condition) {
			String query = "SELECT st.id, bt.bus_type, st.type, b.seat_available " +
					"FROM bus_type bt " +
					"INNER JOIN seat_type st ON st.bus_type = bt.id " +
					"INNER JOIN bus b ON b.bus = st.id " +
					"ORDER BY b.seat_available DESC, bt.bus_type,st.type DESC";
			ResultSet rs = ConnectingToDB.getDataFromDB(query);
			Map<Integer, Integer> busKeyMapping = new LinkedHashMap<>();
			try {
				int index = 1;
				while (rs.next()){
					int id = rs.getInt("id");
					busKeyMapping.put(index, id);
					index++;
				}
				System.out.println("============================\nCHOOSE BUS TYPE:");
				String query1 = "SELECT st.id, bt.bus_type, st.type, b.seat_available " +
						"FROM bus_type bt " +
						"INNER JOIN seat_type st ON st.bus_type = bt.id " +
						"INNER JOIN bus b ON b.bus = st.id " +
						"ORDER BY b.seat_available DESC, bt.bus_type,st.type DESC";

				ResultSet rs1 = ConnectingToDB.getDataFromDB(query1);
				Map<Integer, List<String>> busMapping = new LinkedHashMap<>();
				int inner_index = 1;
				while (rs1.next()){
					int id = rs1.getInt("id");
					String busType = rs1.getString("bus_type");
					String seatType = rs1.getString("type");
					int available = rs1.getInt("seat_available");
					System.out.println(inner_index+". "+ busType+" "+ seatType+" "+available+" seats");
					busMapping.put(id, Arrays.asList(busType, seatType));
					inner_index++;
				}
				try {
					int choice = sc.nextInt();
					if(choice > 0 && choice <=4) {
						int busID = busKeyMapping.get(choice);
						List<String> bus_type = busMapping.get(busID);
						newTicketObjectCreate(busID, bus_type.get(0), bus_type.get(1));
						condition = true;
					} else{
						System.out.println("==================================\nERROR MESSAGE: UNEXPECTED VALUE\n==================================");
					}
				} catch (Exception e){
					sc = new Scanner(System.in);
					System.out.println("==================================\nINPUT SHOULD BE AN INTEGER\n==================================");
				}
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	private void newTicketObjectCreate(int bus_id, String busType, String seatType) {
		ResultSet resultSet = ConnectingToDB.getDataFromDB("SELECT * FROM bus WHERE bus="+bus_id);
		try {
			while (resultSet.next()){
				int availableSeats = resultSet.getInt("seat_available");
				int bookedSeats = resultSet.getInt("seat_occupied");
				double ticketFare = resultSet.getDouble("fare");
				ZoBus bus = new ZoBus(bus_id, busType, seatType, availableSeats, bookedSeats, ticketFare);
				bus.viewSeats();
				Map<Integer, String> bookedSeatDetails = bus.bookTickets();
				if(bookedSeatDetails != null) {
					int noOfTickets = bookedSeatDetails.size();
					int cancelledTickets = 0;
					double bookedFare = bus.calculateFare(false, noOfTickets);
					double CFare = 0;

					PreparedStatement pst = ConnectingToDB.insertDataToDB("INSERT INTO tickets_detail (bus, no_of_seats_booked, no_of_seats_cancelled, booked_fare, cancelled_fare, user_id ) " +
							"VALUES (?,?,?,?,?,?)");
					pst.setInt(1, bus_id);
					pst.setInt(2, noOfTickets);
					pst.setInt(3, cancelledTickets);
					pst.setDouble(4, bookedFare);
					pst.setDouble(5, CFare);
					pst.setInt(6, customerID);
					pst.executeUpdate();

					ResultSet rs = ConnectingToDB.getDataFromDB("SELECT ticket_id " +
							"from tickets_detail " +
							"WHERE user_id="+customerID);

					while (rs.next()) {
						int ticketID = rs.getInt("ticket_id");
						for (int key : bookedSeatDetails.keySet()) {
							PreparedStatement pst1 = ConnectingToDB.insertDataToDB("INSERT INTO booked_Seat_details" +
									" VALUES (?, ?, ?)");
							pst1.setInt(1, ticketID);
							pst1.setInt(2, key);
							pst1.setString(3, bookedSeatDetails.get(key));
							pst1.executeUpdate();
						}
						System.out.println("==================================\nTICKET BOOKED SUCCESSFULLY\n==================================");
					}
				}
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
