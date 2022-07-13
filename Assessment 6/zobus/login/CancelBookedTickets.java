package zobus.login;
import java.util.*;
import zobus.main.*;
import static zobus.main.BusTicketBookingApp.*;
public class CancelBookedTickets {
	public void cancelTickets(PassengerDetails currentPassenger){
		if (ticketBookingsObject.size() > 0) {      // if ticketBookingsObject size will be greater than 0 then only this flow works
			for (TicketBooking ticketBooking : ticketBookingsObject) {
				if (ticketBooking.customerID == currentPassenger.id) {      // directly accessed by the user by checking currentPassenger ID with ticking booking ID has customer ID
					ZoBus bookedBus = ticketBooking.ticketDetails();
					ArrayList<Integer> bookedSeats = new ArrayList<>(ticketBooking.bookedSeatDetails.keySet());       // this keySet are the seat number of passenger booked
					boolean cancellation = true;

					boolean condition3_inner = false;
					while (!condition3_inner) {             // this loop will run until we get valid input from the user
						try {
							System.out.print("1. Cancel all\n2. Delete partially\n");
							sc = new Scanner(System.in);
							int choice = sc.nextInt();
							switch (choice) {
								case 1 -> {         // delete all the tickets by removing object from the ticketBookingsObject
									removingBookedTickets(bookedSeats, choice, cancellation, ticketBooking, bookedBus);
									System.out.println("===================================\nSuccessfully cancelled All Tickets..!\n===================================");
									condition3_inner = true;
								}
								case 2 -> {         // delete the tickets partially
									removingBookedTickets(bookedSeats, choice, cancellation, ticketBooking, bookedBus);
									System.out.println("===================================\nSelected Tickets are cancelled..!\n===================================");
									condition3_inner = true;
								}
								default -> System.out.println("======================\nError message: Unexpected value: " + choice + "\n======================");
							}
						} catch (InputMismatchException inputMismatchException) {
							System.out.println("===============================\nError message: Input Mismatch\n===============================");
						}
					}
				}
			}
		} else {
			System.out.println("===============================================\nSorry your not booked any tickets to cancel\n===============================================");
		}
	}

	private void removingBookedTickets(ArrayList<Integer> bookedSeats, int choice, boolean cancellation, TicketBooking ticketBooking, ZoBus bookedBus) {
		ArrayList<Integer> deletedSeats = bookedBus.deleteTickets(choice, bookedSeats);
		ticketBooking.cancellationFare = bookedBus.calculateFare(cancellation, deletedSeats.size());
		ticketBooking.noOfTicketsDeleted = bookedBus.cancelledSeats;
		for (int seats : deletedSeats)
			for (int i = 0; i < ticketBooking.bookedSeatDetails.size(); i++)
				ticketBooking.bookedSeatDetails.remove(seats);        // removing seats from the map based on the key value(seat number is key here)

		ticketBooking.noOfTicketsBooked = ticketBooking.noOfTicketsBooked - deletedSeats.size();        // noOfTickets is considered as customer booked tickets count
		ticketBooking.fare = (ticketBooking.fare - ticketBooking.cancellationFare);         // calculating the booked fare with cancelled seats fare

	}
}
