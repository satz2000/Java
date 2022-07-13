package zobus.main;
import zobus.utils.*;
import java.util.*;

public class ZoBus implements Bus{
    public String busType;
    public String seatType;
    public final int totalNoOfSeats = 12;
    //public int noOfTicket;      // getting from the user how many tickets they want to book
    public int available =totalNoOfSeats;
    public int bookedSeats=0;
    public int cancelledSeats = 0;
    int[][] seatView;
    public double fare;
    Scanner sc = new Scanner(System.in);
    public ZoBus(String busType, String seatType, double fare) { // constructor
        this.busType = busType;
        this.seatType = seatType;
        this.fare = fare;
        // creating matrix for based on seat type
        if(seatType.equalsIgnoreCase(SeatType.SLEEPER.name())){
            seatView = new int[6][2];
            int counter = 1;
            for (int i=0; i<seatView.length; i++){
                for(int j=0; j<seatView[0].length; j++){
                    seatView[i][j] = counter;
                    counter++;
                }
            }
        }else {
            int counter = 1;
            seatView = new int[4][3];
            for (int i = 0; i < seatView.length; i++) {
                for (int j = 0; j < seatView[0].length; j++) {
                    seatView[i][j] = counter;
                    counter++;
                }
            }
        }
    }
    @Override
    public void viewSeats(){
        System.out.println("====="+busType.toUpperCase()+" "+seatType.toUpperCase()+"=====");
        for (int i=0; i<seatView.length; i++){
            for (int j=0; j<seatView[0].length; j++) {
                if (seatView[i][j] > 0) {
                    System.out.print("    "+seatView[i][j]+" ");
                } else if (seatView[i][j] == -1) {
                    System.out.print("    M ");
                } else {
                    System.out.print("    F ");
                }
            }
            System.out.println();
            if(seatView.length == 6) {
                if ((i + 1) % 3 == 0) {
                    System.out.println("====================");
                }
            }else {
                if((i+1)%2==0){
                    System.out.println("====================");
                }
            }
            System.out.print("");
        }
    }
    public void viewSeatOnlyAdmin(){
        System.out.println("=========="+busType.toUpperCase()+" "+seatType.toUpperCase()+"==========");
        for (int i=0; i<seatView.length; i++){
            for (int j=0; j<seatView[0].length; j++) {
                if (seatView[i][j] > 0) {
                    System.out.print("   Available   ");
                }  else {
                    System.out.print("  - Booked -  ");
                }
            }
            System.out.println();
            if(seatView.length == 6) {
                if ((i + 1) % 3 == 0) {
                    System.out.println("=============================");
                }
            }else {
                if((i+1)%2==0){
                    System.out.println("============================================");
                }
            }
            System.out.print("");
        }
    }
    @Override
    public Map<Integer, String> ticketBook() {
        Map<Integer, String> seats = null;
        boolean condition = false;

        while (!condition) {
            seats = new LinkedHashMap<>();
            try {
                System.out.print("Enter no of tickets you want: ");
                sc = new Scanner(System.in);
                int noOfTicket = sc.nextInt();
                int seatNo;     // getting seat number from user
                String passengerName;

                if (noOfTicket <= available) {
                    Map<Integer, Integer> checkedSeatsToBook = new LinkedHashMap<>();

                    int ticketIncrement = 0;
                    while (ticketIncrement<noOfTicket) {
                        try {
                            System.out.print("Enter seat number from 1 to 12: ");
                            sc = new Scanner(System.in);
                            seatNo = sc.nextInt();
                            if (!seats.containsKey(seatNo)) {

                                if (seatNo < 0 || seatNo > 12) {
                                    System.out.println("==================================\nChoose seat number between 1 to 12\n==================================");

                                } else if (checkAvailability(seatNo)) {
                                    System.out.println("Already booked Please choose available one");

                                } else {
                                    System.out.print("Enter a passenger name: ");
                                    passengerName = sc.next();
                                    boolean genderCondition = false;
                                    while (!genderCondition) {
                                        System.out.print("Enter gender of that person(Male or Female): ");
                                        char gender = sc.next().charAt(0);
                                        int gender_value = 0;
                                        if (gender == 'F' || gender == 'f') {
                                            gender_value = -2;
                                        } else if (gender == 'M' || gender == 'm') {
                                            gender_value = -1;
                                        } else {
                                            System.out.println("======================================\nError message: Invalid input\n======================================");
                                        }

                                        int matcher = 0;
                                        for (int i = 0; i < seatView.length; i++) {
                                            for (int j = 0; j < seatView[0].length; j++) {

                                                if (seatView[i][j] == seatNo) {
                                                    if (gender_value == -2) {
                                                        checkedSeatsToBook.put(seatNo, gender_value);
                                                        ticketIncrement++;
                                                        matcher++;
                                                        genderCondition = true;
                                                        System.out.println("============================================\nYour selected [ seat no: " + seatNo + "] to booking\n============================================");

                                                    } else if (gender_value == -1) {
                                                        if (((i + 1) % 2 != 0 && seatView[i + 1][j] == -2) || ((i + 1) % 2 == 0 && seatView[i - 1][j] == -2)) {
                                                            System.out.println("Sorry Male cannot sit with female neighbour\nTry another with male neighbour or free seats");
                                                            genderCondition = true;

                                                        } else {
                                                            checkedSeatsToBook.put(seatNo, gender_value);
                                                            ticketIncrement++;
                                                            matcher++;
                                                            genderCondition = true;
                                                            System.out.println("============================================\nYour selected [ seat no: " + seatNo + "] to booking\n============================================");
                                                        }
                                                    }
                                                    break;
                                                }
                                                if (matcher > 0) {
                                                    break;
                                                }
                                            }
                                        }
                                        if (seatNo != 0) {
                                            seats.put(seatNo, passengerName);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("======================================\nTickets already selected by you\n======================================");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("==========================\nRequires integer value\n==========================");
                        }
                    }

                    seats = confirmationSeats(seats, checkedSeatsToBook);   // calling the private methods to confirm the ticket booking
                    condition = true;       // after confirming or cancelling the selected seats, this will make condition false

                }else {
                    seats = null;
                    System.out.println("==============================================\nRequired seats are greater than available seats\n==============================================");
                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println("==========================\nError message: Input Mismatch\n==========================");
            }
        }
        return seats;
    }
    private Map<Integer, String> confirmationSeats(Map<Integer, String> seats, Map<Integer, Integer> checkedSeatsToBook){
        System.out.println("==============================================\nTicket Confirmation: ");
        for (int i: seats.keySet()) {
            System.out.println("[Seat no: " + i
                    + " - Passenger name: " + seats.get(i) + "]");
        }
        double fare = calculateFare(false, checkedSeatsToBook.size());
        System.out.println("Total fare to pay :" + checkedSeatsToBook.size() +" x "+ fare/checkedSeatsToBook.size()+" = "+ fare);
        System.out.println("==============================================");
        boolean flags = false;
        while (!flags) {
            try {
                System.out.println("Confirm your ticket: \n1. Confirm \n2. Cancel");
                sc = new Scanner(System.in);
                int confirm = sc.nextInt();
                switch (confirm) {
                    case 1: {
                        bookedSeats += seats.size();
                        available = available - bookedSeats;
                        // after checking the availability, book the seats
                        for (int next = 0; next < checkedSeatsToBook.size(); next++) {
                            for (int i = 0; i < seatView.length; i++) {
                                for (int j = 0; j < seatView[0].length; j++) {
                                    if (checkedSeatsToBook.containsKey(seatView[i][j])) {
                                        seatView[i][j] = checkedSeatsToBook.get(seatView[i][j]);
                                    }
                                }
                            }
                        }
                        flags = true;
                        break;
                    }
                    case 2: {
                        bookedSeats = 0;
                        seats = null;
                        flags = true;
                        break;
                    }
                    default:
                        System.out.println("======================\nUnexpected value: " + confirm + "\n======================");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("==========================\nRequires integer value\n==========================");
            }
        }
        return seats;
    }
    @Override
    public boolean checkAvailability(int seatNo){       // checking the availability of the seat number in selected bus
        boolean flag = false;

        int seat = 0;       // initializing the value 0 for seat to checking with seat number
        for(int i=0; i<seatView.length; i++){
            for(int j=0; j<seatView[0].length; j++){
                seat++;
                if(seat == seatNo){
                    flag = seatView[i][j] == -1 || seatView[i][j] == -2;
                }
            }
        }
        return flag;
    }
    public ArrayList<Integer> deleteTickets(int choice, ArrayList<Integer> bookedTickets){
        ArrayList<Integer> deletedSeats = new ArrayList<>();
        if(choice == 1) {       // will delete all tickets, and finally remove the ticketBooking object
            for (int ticket : bookedTickets) {
                int seat = 1;
                for (int i = 0; i < seatView.length; i++) {
                    for (int j = 0; j < seatView[0].length; j++) {
                        if (seat == ticket) {
                            seatView[i][j] = seat;
                            bookedSeats--;
                            available = totalNoOfSeats-bookedSeats;
                            deletedSeats.add(seat);
                        }
                        seat++;
                    }
                }
            }
            cancelledSeats += bookedTickets.size();     // assigning the noOfTicket to the cancelledSeats

        }else {
            System.out.println("Total number seats booked : " + bookedTickets.size() + "\nseat numbers are: " + bookedTickets);
            int increment = 1;
            while (increment<= bookedTickets.size()) {
                    int seatForCancel;
                    try {
                        System.out.print("Enter a seat number to cancel: ");
                        sc = new Scanner(System.in);
                        seatForCancel = sc.nextInt();
                        if (bookedTickets.contains(seatForCancel)) {        // checking if user already entered the seat number
                            if (!deletedSeats.contains(seatForCancel)) {    // checking user entered seat number is in booked seat number
                                int seat = 1;
                                for (int i = 0; i < seatView.length; i++) {
                                    for (int j = 0; j < seatView[0].length; j++) {
                                        if (seatForCancel == seat) {
                                            seatView[i][j] = seat;
                                            bookedSeats--;
                                            available = totalNoOfSeats - bookedSeats;
                                            cancelledSeats++;
                                            deletedSeats.add(seat);
                                            System.out.println("======================================\n[ Seat number "+ seat +" are deleted ]\n======================================");
                                        }
                                        seat++;
                                    }
                                }
                                try {
                                    if (increment == bookedTickets.size()) {    // booked ticket count matched to no of tickets deleted..
                                        break;

                                    } else if (increment < bookedTickets.size()) {
                                        System.out.print("======================================\nWant to delete more tickets? \n1. Yes\n2. No\n");
                                        int nextDelete = sc.nextInt();
                                        if (nextDelete == 2) {
                                            increment = bookedTickets.size() + 1;
                                            break;
                                        }
                                    }
                                } catch (InputMismatchException inputMismatchException) {
                                    System.out.println("==========================\nRequires integer value\n==========================");
                                }
                                increment++;
                            } else {
                                System.out.println("======================================\nYour selected same seat number again\n======================================");
                            }
                        } else {
                            System.out.println("=====================================\nEntered wrong seat number!!!\n=====================================");
                        }
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("===============================\nError message: Input Mismatch\n===============================");
                    }
            }
        }
        return deletedSeats;
    }
    @Override
    public double calculateFare(boolean cancellation, int bookedSeats){
        double fareCalculation = 0.0;
        if (cancellation){
            if (busType.equalsIgnoreCase(BusType.AC.name()) && seatType.equalsIgnoreCase(SeatType.SLEEPER.name())) {
                fareCalculation = bookedSeats * (fare/2);
            } else if (busType.equalsIgnoreCase(BusType.AC.name()) && seatType.equalsIgnoreCase(SeatType.SEATER.name())) {
                fareCalculation = bookedSeats * (fare/2);
            } else if (busType.equalsIgnoreCase(BusType.NONAC.name()) && seatType.equalsIgnoreCase(SeatType.SLEEPER.name())) {
                fareCalculation = bookedSeats * (fare/4);
            } else if (busType.equalsIgnoreCase(BusType.NONAC.name()) && seatType.equalsIgnoreCase(SeatType.SEATER.name())){
                fareCalculation = bookedSeats * (fare/4);
            }else {
                System.out.println("Bus type or seat type does not match!");
            }
        }else {
            if (busType.equalsIgnoreCase(BusType.AC.name()) && seatType.equalsIgnoreCase(SeatType.SLEEPER.name())) {
                fareCalculation = bookedSeats * fare;
            } else if (busType.equalsIgnoreCase(BusType.AC.name()) && seatType.equalsIgnoreCase(SeatType.SEATER.name())) {
                fareCalculation = bookedSeats * fare;
            } else if (busType.equalsIgnoreCase(BusType.NONAC.name()) && seatType.equalsIgnoreCase(SeatType.SLEEPER.name())) {
                fareCalculation = bookedSeats * fare;
            } else if (busType.equalsIgnoreCase(BusType.NONAC.name()) && seatType.equalsIgnoreCase(SeatType.SEATER.name())){
                fareCalculation = bookedSeats * fare;
            }else {
                System.out.println("Bus type or seat type does not match!");
            }
        }
        return fareCalculation;
    }
}
