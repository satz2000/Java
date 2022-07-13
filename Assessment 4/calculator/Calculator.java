package calculator;

//Achieving multiple inheritance by using Implementation of two interface in one class, also inherits the abstract class methods 
public class Calculator extends AbstractAdvanceCalculator implements OperatorInterface, AdvanceOperation { 		
	
	@Override
	public void addition(double number1, double number2) {
		System.out.println("Sum of two number is, "+ (number1 + number2));
	}
	
	@Override
	public void subraction(double number1, double number2) {
		System.out.println("subraction of two number is, "+ (number1 - number2));
	}
	
	@Override
	public void multiplication(double number1, double number2) {
		System.out.println("Multiplication of two number is, "+ (number1 * number2));
	}
	
	@Override
	public void division(double number1, double number2) {
		if (UserInputClass.number2 != 0){
			System.out.println("Division of two number is, "+ (number1 / number2));
		}else {
			System.out.println("Any number is Divisible by Zero is not possible!");
		}	
	}	
	
	@Override
	public void modularDivision(double number1, double number2) {		// using the abstract methods by overriding it in calculator class by extend the abstract class
		System.out.println("Modulor division of two number is, "+ (number1 % number2));
	}
}
