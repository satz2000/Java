package zobus.main;
import java.util.Map;

public class TicketBooking {
    public int ticketId;
    public ZoBus bus;
    public Map<Integer, String> bookedSeatDetails;
    public int noOfTicketsDeleted;
    public int noOfTicketsBooked;
    public double fare;
    public double cancellationFare;
    public int customerID;

    public TicketBooking(int ticketId, ZoBus bus, Map<Integer, String> bookedSeatDetails, int noOfTicketsDeleted, int noOfTicketsBooked, double fare, double cancellationFare, int customerID) {
        this.ticketId = ticketId;
        this.bus = bus;
        this.bookedSeatDetails = bookedSeatDetails;
        this.noOfTicketsBooked = noOfTicketsBooked;
        this.fare = fare;
        this.noOfTicketsDeleted = noOfTicketsDeleted;
        this.cancellationFare = cancellationFare;
        this.customerID = customerID;
    }
    public ZoBus ticketDetails()
    {
        System.out.println("===========================");
        System.out.println("Ticket details are:\n");
        System.out.println("Bus type:"+bus.busType);
        System.out.println("Seat type:"+bus.seatType);
        System.out.println("Booked by the customer id:"+customerID);
        System.out.println("No of tickets:"+noOfTicketsBooked);
        System.out.println("Booked seats:"+bookedSeatDetails.toString());
        System.out.println("===========================");
        return bus;
    }
}
