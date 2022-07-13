package calculator;

public class CalculatorApp extends UserInputClass{		// inherits the UserInputClass for accessing the methods without creating instance for it 

	public static void main(String[] args) {
		
		CalculatorApp app = new CalculatorApp();		//   creating object for CalculatorApp class
		
		System.out.println("----------------Execution Started----------------");
		app.getValFromUser();		// call the methods to get input from the user...
		System.out.println("----------------Execution Finished---------------");
	}

}
