package zobus.main;
import zobus.admin.Admin;
import java.util.*;

public class BusTicketBookingApp extends Admin implements CreateDefaultObject{
    public static ArrayList<ZoBus> busObject = new ArrayList<>();                               // to store list of bus objects
    public static ArrayList<TicketBooking> ticketBookingsObject = new ArrayList<>();            // to store list of ticking booking objects
    public static ArrayList<PassengerDetails> passengerDetailsObject = new ArrayList<>();       // to store list of passenger details objects
    public static Scanner sc = new Scanner(System.in);                                          // creating scanner object for getting input from the keyboard

    public static void main(String[] args) {

        BusTicketBookingApp app = new BusTicketBookingApp();
        // createObject methods is in the CreateDefaultObject interface by implementing in the main class
        app.createObject();         // create an object based on some initial data

        int choice;
        while (true) {
            try {
                System.out.print("====== ZOBUS APP ======\n1. Admin \n2. User Sign-up\n3. User Login\n4. Exit..!\nChoose any one of them: ");
                sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> app.adminLogin();         // call the adminLogin method from Admin class
                    case 2 -> app.userSignUp();         // call userSignUp method from UserSignUpActivity class
                    case 3 -> app.userLogin();          // call userLogin method from UserSignUpActivity which extends the UserLoginActivity class
                    case 4 -> System.exit(0);
                    default -> System.out.println("======================\nUnexpected value: " + choice + "\n======================");
                }
            } catch (InputMismatchException e){
                System.out.println("=======================================\nInput should be an integer value\n=======================================");
            }
        }

    }

}
