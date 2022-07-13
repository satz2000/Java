package calculator;

// creating an interface for hiding the implementation from the end user, only showing the functionality
interface OperatorInterface {
	
	void addition(double number1, double number2);		// declare the methods
	void subraction(double number1, double number2);	
		
}

// creating another interface for achieving the multiple inheritance with the help of interfaces
interface AdvanceOperation {
	
	void multiplication(double number1, double number2);	// declare the methods
	void division(double number1, double number2);
	
}
