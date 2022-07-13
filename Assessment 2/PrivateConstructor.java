class Demo{
	
	// create another constructor for passing arguments
	private Demo() {
		
	}
	
	//these methods use return type as object
	public static Demo callPrivateConstructor() {
		return new Demo();		// creating object for the class demo, 
		// Inside a class because of private constructor cannot access in outside class
	}
}

// Main class
public class PrivateConstructor {
	
	public static void main(String[] args) {
		Demo obj = Demo.callPrivateConstructor();
		// print the object reference address
		System.out.println(obj);
	}
}
