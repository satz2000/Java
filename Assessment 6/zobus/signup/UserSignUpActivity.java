package zobus.signup;

import java.util.*;
import static zobus.main.BusTicketBookingApp.*;
import zobus.login.UserLoginActivity;
import zobus.main.PassengerDetails;

public class UserSignUpActivity extends UserLoginActivity{
	 protected void userSignUp() {
		try {
			System.out.println("================================\nWelcome to User Sign-up portal:");
			int id = passengerDetailsObject.size();
			System.out.print("Enter your name: ");
			String name = sc.next();
			String password ;

			int exists = 0;
			for (PassengerDetails passengerDetails : passengerDetailsObject)
				if (passengerDetails.name.equalsIgnoreCase(name)) {
					exists++;
					break;
				}

			if(exists == 0){
				System.out.print("Create a password: ");
				password = sc.next();
				boolean flags = false;
				while (!flags) {
					try {
						System.out.print("Enter your age: ");
						sc = new Scanner(System.in);
						int age = sc.nextInt();
						System.out.print("Enter your gender: ");
						char gender = sc.next().charAt(0);
						passengerDetailsObject.add(new PassengerDetails(id, name, password, age, gender));
						flags = true;
						System.out.println("================================\nNew account created successfully,\nLogin with your Account\n================================");
					}catch (InputMismatchException inputMismatchException){
						System.out.println("================================\nInput mismatch to required field\n================================");
					}
				}
			} else{
				System.out.println("================================\nUsername Already exists..! \ntry different one(or)try to Login\n================================");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
