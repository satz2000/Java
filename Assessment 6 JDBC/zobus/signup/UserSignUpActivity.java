package zobus.signup;
import java.sql.*;
import java.util.*;
import static zobus.main.BusTicketBookingApp.*;
import zobus.database.ConnectingToDB;
import zobus.login.UserLoginActivity;

public class UserSignUpActivity extends UserLoginActivity {
	final String ROLE = "USER";
	String name;
	String password;
	int age;
	String gender;

	protected void userSignUp() {
		if (userNotExists()) {
			getInformationFromUser();       // getting user data by calling this method
			// after getting all the information with matching data type, insert into database
			try {
				PreparedStatement pst = ConnectingToDB.insertDataToDB("INSERT INTO login_credentials(username, password, role) VALUES(?, ?, ?)");
				pst.setString(1, name);
				pst.setString(2, password);
				pst.setString(3, ROLE);
				pst.executeUpdate();

				// inserting values into user data table
				ResultSet rs = ConnectingToDB.getDataFromDB("SELECT * FROM login_credentials WHERE role='" + ROLE + "' AND username='" + name + "'");
				int user_id;
				while (rs.next()) {
					user_id = rs.getInt("id");

					PreparedStatement pst1 = ConnectingToDB.insertDataToDB("INSERT INTO user_data (user_id, age, gender) VALUES (?, ?, ?)");
					pst1.setInt(1, user_id);
					pst1.setInt(2, age);
					pst1.setString(3, gender);
					pst1.executeUpdate();
					System.out.println("================================\nACCOUNT CREATED SUCCESSFULLY\nLOGIN WITH YOUR ACCOUNT\n================================");
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}

		}else {
			System.out.println("================================\nUsername Already exists..! \ntry different one(or)try to Login\n================================");
		}
	}
	private boolean userNotExists(){
		boolean notExists = false;
		System.out.println("================================\nWELCOME USER SIGN-UP PORTAL:");
		System.out.print("Enter your name: ");
		name = sc.next();

		String query = "SELECT username FROM login_credentials WHERE username='"+name+"' AND role='"+ROLE+"'";
		ResultSet resultSet = ConnectingToDB.getDataFromDB(query);

		try {
			if (!resultSet.next()) {
				notExists = true;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return notExists;
	}
	private void getInformationFromUser(){
		System.out.print("Create a password: ");
		password = sc.next();
		boolean ageFlag = false;
		while (!ageFlag) {
			try {
				System.out.print("Enter your age: ");
				age = sc.nextInt();
				boolean genderFlag = false;
				while (!genderFlag) {
					System.out.print("Enter your gender: ");
					gender = sc.next();
					if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
						ageFlag = true; genderFlag = true;
					} else {
						System.out.println("===============================\nGENDER SHOULD BE Male OR Female");
					}
				}
			} catch (InputMismatchException inputMismatchException) {
				sc = new Scanner(System.in);
				System.out.println("================================\nINPUT SHOULD BE AN INTEGER\n================================");
			}
		}

	}
}