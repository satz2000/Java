package calculator;

class CallOperatorClass {
	
	void callMethods(char operator) {
		Calculator calOp = new Calculator();
		
		// using switch case condition to check the input operator and perform some action based on the value from the user
		switch (operator) {
		case '+': {
			calOp.addition(UserInputClass.number1, UserInputClass.number2);
			break;
			
		} case '-': {
			calOp.subraction(UserInputClass.number1, UserInputClass.number2);
			break;
			
		} case '*': {
			calOp.multiplication(UserInputClass.number1, UserInputClass.number2);
			break;
			
		} case '/': {
			calOp.division(UserInputClass.number1, UserInputClass.number2);
			break;
			
		} case '%': {
			calOp.modularDivision(UserInputClass.number1, UserInputClass.number2);
			break;
			}
		}
	}
}
