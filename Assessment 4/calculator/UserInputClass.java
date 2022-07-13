package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputClass {
	
	static double number1;
	static double number2;
	static char operator;
	
	protected void getValFromUser() {
		Scanner userInput = new Scanner(System.in);	// creating a scanner object
		
		try {
			
			System.out.print("Enter a 1st number: ");
			number1 = userInput.nextDouble();
			
			System.out.print("Choose an operator [+, -, *, /, %] : ");
			operator = userInput.next().charAt(0);
			
			System.out.print("Enter a 2nd number: ");
			number2 = userInput.nextDouble();
			
			// close the scanner object
			userInput.close();			
			
		} catch (InputMismatchException ime) {
			System.out.println("Invalid input! Please enter a number");		
			
		} finally {
			if(operator == 0) {
				System.out.println("----------------Execution Finished----------------");
				System.exit(0);
			
			}else if (operator != '+' & operator != '-' & operator != '*' & operator != '/' & operator != '%') {
				System.out.println("Invalid operator!");
			} else {
				// objects for CallOperatorClass to access the methods
				CallOperatorClass op = new CallOperatorClass();
				op.callMethods(operator);	// all inputs will be stored in variable, then call the methods based on operator
			}
			
		}	
	}
}
