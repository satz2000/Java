// writing a code for Find the factorial of given number
import java.util.Scanner;

public class FactorialNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);      		// creating object for scanner class
		
		System.out.println("Enter a number: ");
		int number = sc.nextInt();						// reading input from the keyboard
		
		System.out.println(recursionMethod(number));  // directly calling function and prints in it
	}
	
	public static int recursionMethod(int number) {
		
		if (number==0) {		// setting the base number
			return 1;
		}
		
		else {     			// eg, 5 * method(5-1)
			return number * recursionMethod(number-1);      // recursion methods, calling a methods by itself
		}	
	}
}
