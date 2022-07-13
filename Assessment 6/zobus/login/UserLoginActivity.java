package zobus.login;

import zobus.main.*;
import zobus.utils.BusType;
import java.util.*;
import static zobus.main.BusTicketBookingApp.*;

public class UserLoginActivity {          // child class of UserSignUpActivity
	public void userLogin() {
		System.out.println("===================\nUser Login Portal:\n===================");
		boolean flags = false;
		while (!flags) {
			try {
				System.out.print("Enter username: ");
				String userName = sc.next();
				// mapping the matched account to the current passenger to booking tickets in his account
				PassengerDetails currentPassenger = null;
				int existsUserName = 0;
				for (PassengerDetails passengerDetails: passengerDetailsObject){
					if (passengerDetails.name.equalsIgnoreCase(userName)) {
						existsUserName++;

						boolean passFlag = false;
						while (!passFlag) {
							System.out.print("Enter password: ");
							String password = sc.next();
							if (passengerDetails.password.equals(password)) {
								currentPassenger = passengerDetails;
								passFlag = true;

							} else {
								System.out.println("===============================\nError message: Invalid password\n===============================");
							}
						}
					}
				}
				if (existsUserName == 0){
					System.out.println("===============================\nError message: Invalid username\n===============================");

				} else {
					boolean condition = false;
					while (!condition) {
						try {
							System.out.println("------ Home Page ------\n1. Book Tickets\n2. View Booked Tickets\n3. Cancel Tickets\n4. Logout");

							sc = new Scanner(System.in);
							int userChoice = sc.nextInt();

							switch (userChoice) {
								case 1 -> {
									int matcher = 0;
									if (ticketBookingsObject.size() > 0) {
										for (TicketBooking ticketBooking : ticketBookingsObject) {
											if (ticketBooking.customerID == currentPassenger.id) {
												matcher++;
											}
										}
									}

									if (matcher==0) {
										busSelectionMethods(currentPassenger);  // for booking the tickets
									} else {
										System.out.println("=======================================\nOne customer can book one tickets only\n=======================================");
									}
								}

								case 2 -> new ViewBookedTickets().viewTickets(currentPassenger); // for view the booked tickets

								case 3 -> new CancelBookedTickets().cancelTickets(currentPassenger); // for cancelling the tickets

								case 4 -> {
									condition = true;
									flags = true;
									System.out.println("================================\nLogout successfully!\nCome back again " + currentPassenger.name + "!!!\nThank You\n================================");
								}
								default -> System.out.println("======================\nError message: Unexpected value: "+ userChoice+"\n======================");

							}

						} catch (InputMismatchException inputMismatchException) {
							System.out.println("======================\nInput should be an Integer\n======================");
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void busSelectionMethods(PassengerDetails currentPassenger) {       // bus selection methods based on filter on available seats

		boolean condition = false;
		while (!condition) {
			try {
				System.out.println("Choose bus type:\n1. AC\n2. NON-AC\n3. Both\n");
				sc = new Scanner(System.in);        // reset the scanner object, every time getting wrong input type value
				int choice1 = sc.nextInt();
				switch (choice1) {
					case 1 -> {
						Map<Integer, List<String>> bothSeatTypeMap = new LinkedHashMap<>();         // storing the sorted bus ordered in map to matching the user input
						// sort the buses based on available seats
						busObject = sortBusBasedOnAvailableSeats();

						int index = 1;
						for (int i = 0; i < busObject.size(); i++) {
							if (busObject.get(i).busType.equalsIgnoreCase(BusType.AC.name())) {
								if (busObject.get(i).available != 0) {
									bothSeatTypeMap.put(index , Arrays.asList(busObject.get(i).busType, busObject.get(i).seatType, String.valueOf(busObject.get(i).available)));
									index++;
								}
							}
						}
						boolean innerCondition = false;
						while (!innerCondition) {
							try {
								StringBuilder sb = new StringBuilder();
								sb.append("=====================================\nChoose Seat Type: \n");
								bothSeatTypeMap.forEach((k, v) -> sb.append(k).append(". ").append(v.get(0)).append(" ").append(v.get(1)).append(" ").append(v.get(2)).append(" seats").append("\n"));
								System.out.println(sb);

								sc = new Scanner(System.in);
								int choice_inner_1 = sc.nextInt();
								if (choice_inner_1 > 0 && choice_inner_1 <= 2) {
									List<String> seatType = bothSeatTypeMap.get(choice_inner_1);
									newTicketObjectCreate(currentPassenger, seatType.get(0), seatType.get(1));
									condition = true;
									innerCondition = true;

								} else {
									System.out.println("======================\nError message: Unexpected value\n======================");
								}

							} catch (InputMismatchException inputMismatchException) {
								System.out.println("======================\nInput should be an Integer\n======================");
							}
						}
					}
					case 2 -> {

						Map<Integer, List<String>> bothSeatTypeMap = new LinkedHashMap<>();
						// sort the buses based on available seats
						busObject = sortBusBasedOnAvailableSeats();

						int index = 1;
						for (int i = 0; i < busObject.size(); i++) {
							if (busObject.get(i).busType.equalsIgnoreCase(BusType.NONAC.name())) {
								if (busObject.get(i).available != 0) {
									bothSeatTypeMap.put(index , Arrays.asList(busObject.get(i).busType, busObject.get(i).seatType, String.valueOf(busObject.get(i).available)));
									index++;
								}
							}
						}
						boolean innerCondition = false;
						while (!innerCondition) {
							try {
								StringBuilder sb = new StringBuilder();
								sb.append("=====================================\nChoose Seat Type: \n");
								bothSeatTypeMap.forEach((k, v) -> sb.append(k).append(". ").append(v.get(0)).append(" ").append(v.get(1)).append(" ").append(v.get(2)).append(" seats").append("\n"));
								System.out.println(sb);

								sc = new Scanner(System.in);
								int choice_inner_1 = sc.nextInt();
								if (choice_inner_1 > 0 && choice_inner_1 <= 2) {
									List<String> seatType = bothSeatTypeMap.get(choice_inner_1);
									newTicketObjectCreate(currentPassenger, seatType.get(0), seatType.get(1));
									condition = true;
									innerCondition = true;

								} else {
									System.out.println("======================\nError message: Unexpected value\n======================");
								}

							} catch (InputMismatchException inputMismatchException) {
								System.out.println("======================\nInput should be an Integer\n======================");
							}
						}
					}

					case 3 -> {
						busObject = sortBusBasedOnAvailableSeats();
						Map<Integer, List<String>> bothBusTypeMap = new LinkedHashMap<>();
						for (int i = 0; i < busObject.size(); i++) {
							if (busObject.get(i).available != 0) {
								bothBusTypeMap.put(i + 1, Arrays.asList(busObject.get(i).busType, busObject.get(i).seatType, String.valueOf(busObject.get(i).available)));
							}
						}
						boolean innerCondition = false;
						while (!innerCondition) {
							try {
								StringBuilder sb = new StringBuilder();
								sb.append("=====================================\nChoose Bus To book ticket: \n");
								bothBusTypeMap.forEach((k, v) -> sb.append(k).append(". ").append(v.get(0)).append(" ").append(v.get(1)).append(" - ").append(v.get(2)).append(" seats").append("\n"));
								System.out.println(sb);

								sc = new Scanner(System.in);
								int case3_choice = sc.nextInt();
								if (case3_choice > 0 && case3_choice <= 4) {
									List<String> type = bothBusTypeMap.get(case3_choice);
									newTicketObjectCreate(currentPassenger, type.get(0), type.get(1));
									innerCondition = true;
									condition = true;

								} else {
									System.out.println("======================\nError message: Unexpected value\n======================");
								}

							} catch (InputMismatchException inputMismatchException) {
								System.out.println("======================\nInput should be an Integer\n======================");
							}
						}
					}
					default -> System.out.println("======================\nError message: Unexpected value\n======================");
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println("======================\nInput should be an Integer\n======================");
			}
		}
	}

	private ArrayList<ZoBus> sortBusBasedOnAvailableSeats() {
		Comparator<ZoBus> comparator1 = (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o2.seatType, o1.seatType);
		busObject.sort(comparator1);
		Comparator<ZoBus> comparator2 = (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.busType, o2.busType);
		busObject.sort(comparator2);
		Comparator<ZoBus> comparator = (o1, o2) -> Integer.compare(o2.available, o1.available);
		busObject.sort(comparator);
		return busObject;
	}

	private void newTicketObjectCreate(PassengerDetails currentPassenger, String busType, String seatType) {
		for (ZoBus bus : busObject) {
			if (bus.busType.equalsIgnoreCase(busType) && bus.seatType.equalsIgnoreCase(seatType)) {
				bus.viewSeats();
				Map<Integer, String> bookedSeatDetails = bus.ticketBook();
				if (bookedSeatDetails != null) {
					int noOfTicketsBooked = bus.bookedSeats;
					boolean cancellation = false;
					double fare = bus.calculateFare(cancellation, noOfTicketsBooked);
					double cancellationFare = 0;
					int deletedTickets = 0;
					int ticketId = ticketBookingsObject.size() + 1;
					System.out.println("===============================");
					System.out.println("Tickets booked successfully");
					System.out.println("Ticket Booking ID: " + ticketId);
					ticketBookingsObject.add(new TicketBooking(ticketId, bus, bookedSeatDetails, deletedTickets, noOfTicketsBooked, fare, cancellationFare, currentPassenger.id));
					System.out.println("===============================");

				}
			}
		}
	}
}