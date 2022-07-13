package zobus.admin;
import java.util.*;
import zobus.main.*;
import zobus.signup.UserSignUpActivity;
import static zobus.main.BusTicketBookingApp.*;
public class Admin extends UserSignUpActivity {
	public void adminLogin(){
		boolean flags = false;
		while (!flags) {
			try {
				System.out.println("======================\nAdmin Login Portal:");
				System.out.print("Enter UserName: ");
				String adminID = sc.next();
				String password;
				if (adminID.equalsIgnoreCase(passengerDetailsObject.get(0).name)) {
					boolean passwordFlags = false;
					while (!passwordFlags) {
						System.out.print("Enter password: ");
						password = sc.next();
						if (password.equalsIgnoreCase(passengerDetailsObject.get(0).password)) {
							boolean flag = false;
							while (!flag) {
								try {
									System.out.println("====================\nAdmin has access to view\n1. Summary of Bus\n2. Logout");
									sc = new Scanner(System.in);
									int adminChoice = sc.nextInt();
									switch (adminChoice) {
										case 1 -> summary();
										case 2 -> {
											flag = true;
											flags = true;
											passwordFlags = true;
											System.out.println("======================\nLogout successfully!!!\n======================");
										}
										default -> System.out.println("======================\nError message: Unexpected value: " + adminChoice + "\n======================");
									}
								} catch (InputMismatchException inputMismatchException) {
									System.out.println("========================================\nError message: Input requires integer value!!!\n========================================");
								}
							}
						} else {
							System.out.println("========================================\nError message: Invalid Password!!!\n========================================");
						}
					}
				} else {
					System.out.println("========================================\nError message: Invalid Username!!!\n========================================");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	private void summary() {

		for (ZoBus bus : busObject) {
			System.out.println(bus.busType + " " + bus.seatType);
			System.out.println("No of seats occupied: " + bus.bookedSeats);
			System.out.println("No of seats cancelled: " + bus.cancelledSeats);
			System.out.println("Per ticket price: " + bus.fare);
			double totalFareOfSelectedBus = 0;
			double totalFareOfSelectedBus_C = 0;

			for (TicketBooking ticketBooking : ticketBookingsObject) {
				if (ticketBooking.bus.busType.equalsIgnoreCase(bus.busType) && ticketBooking.bus.seatType.equalsIgnoreCase(bus.seatType)) {
					totalFareOfSelectedBus += ticketBooking.fare;
					totalFareOfSelectedBus_C += ticketBooking.cancellationFare;
					if (!ticketBooking.bookedSeatDetails.isEmpty()) {
						System.out.println("Passenger Details: ");
						for (int i : ticketBooking.bookedSeatDetails.keySet()) {
							System.out.println("[ Seat no: " + i + ", Passenger name: " + ticketBooking.bookedSeatDetails.get(i) + " ]");
						}
					}
				}
			}
			System.out.println("Total fare collected: " + (totalFareOfSelectedBus) + " (" + bus.bookedSeats + " Tickets + " + bus.cancelledSeats + " cancellation charge " + totalFareOfSelectedBus_C + " )");
			System.out.println("Seat filled: ");
			bus.viewSeatOnlyAdmin();
			System.out.println("-------------------------------------");

		}
	}
}
