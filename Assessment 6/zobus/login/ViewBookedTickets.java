package zobus.login;
import java.util.*;
import zobus.main.*;
import static zobus.main.BusTicketBookingApp.*;
public class ViewBookedTickets {
	public void viewTickets(PassengerDetails currentPassenger) {
		if (ticketBookingsObject.size() > 0) {
			for (TicketBooking ticketBooking : ticketBookingsObject) {
				if (currentPassenger.id == ticketBooking.customerID) {          // checking internally with customer id matching with passenger ID in tickets
					System.out.println("============================");
					System.out.println("Ticket ID: " + ticketBooking.ticketId);
					System.out.println("Bus Type: " + ticketBooking.bus.busType);
					System.out.println("Seat Type: " + ticketBooking.bus.seatType);
					System.out.println("No.of Tickets Booked: " + ticketBooking.noOfTicketsBooked);

					Set<Integer> seats = ticketBooking.bookedSeatDetails.keySet();    // keySet are stored in a variable

					System.out.println("Seat No: " + seats);
					System.out.println("No.of Tickets Cancelled: " + ticketBooking.noOfTicketsDeleted);
					System.out.println("Fare: " + ticketBooking.fare);
					System.out.println("Customer ID: " + ticketBooking.customerID);
					System.out.println("============================");
				}
			}
		} else {
			System.out.println("=======================================\nYour not booked any tickets previously\n=======================================");
		}
	}
}