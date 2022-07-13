package zobus.main;
import static zobus.main.BusTicketBookingApp.*;
interface CreateDefaultObject {
	default void createObject(){
		busObject.add(new ZoBus("AC", "Sleeper", 700));
		busObject.add(new ZoBus("AC", "Seater", 550));
		busObject.add(new ZoBus("NonAC", "Sleeper", 600));
		busObject.add(new ZoBus("NonAC", "Seater", 450));

		passengerDetailsObject.add(new PassengerDetails(0, "admin", "admin", 0, '-'));
		passengerDetailsObject.add(new PassengerDetails(1, "aaa", "111", 25, 'F'));
		passengerDetailsObject.add(new PassengerDetails(2, "bbb", "222", 61, 'M'));
		passengerDetailsObject.add(new PassengerDetails(3, "ccc", "333", 22, 'M'));
		passengerDetailsObject.add(new PassengerDetails(4, "ddd", "444", 36, 'F'));
	}
}
