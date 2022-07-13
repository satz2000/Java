// writing a code to checking the input value is uppercase, lowercase, digits, special character
import java.util.Scanner;
public class CheckingInputChar {
	public static void main(String[] args) {
		// creating an object for scanner class for calling the methods
		Scanner sc = new Scanner(System.in);
		// getting input from the user
		System.out.print("Enter a character : ");
		char ch = sc.next().charAt(0);    // getting the 1st index value from input
		// using if condition statement to check the input value
		if (ch >= 'A' && ch <='Z') {
			System.out.print("Input value is in UPPERCASE");   // ASCII values for A to Z is 65 to 90 
		} else if (ch >= 'a' && ch <= 'z') {    // ASCII values for a to z is 97 to 122
			System.out.print("Input value is in LOWERCASE");
		} else if (ch >='0' && ch <= '9') {     // ASCII value for 0 to 9 is 48 to 57
			System.out.print("Input value is in NUMERICAL VALUE");
		} else {
			System.out.print("Input value is in SPECIAL CHARACTER");    // 
		}
	}
}
