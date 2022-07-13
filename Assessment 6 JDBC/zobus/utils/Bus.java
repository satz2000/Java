package zobus.utils;
import java.util.Map;

// basic feature of bus application required
public interface Bus {
	void viewSeats();       // viewing the booked seats, based on specific buses
	Map<Integer, String> bookTickets();      // ticket booking methods
	boolean checkAvailability(int seatNo);      // checking the availability of seats, which user wants
	double calculateFare(boolean cancellation, int noOfTickets);    // calculating fare of tickets booked by customer, based on bus type
}
