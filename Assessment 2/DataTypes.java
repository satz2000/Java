import java.util.Arrays;

public class DataTypes {
	
	public static void main(String[] args) {
		byte num1 = 125;
		System.out.println("Byte, "+num1);
		
		short num2 = 13122;
		System.out.println("short, "+num2);
		
		int num3 = 21312124;
		System.out.println("int, "+num3);
		
		long num4 = 234232334433443L;
		System.out.println("long, "+num4);
		
		float num5 = 2.4F;
		System.out.println("float, "+num5);
		
		double num6 = 22.34;
		System.out.println("double, "+num6);
		
		char character = 'A';
		System.out.println("char, "+character);
		
		boolean bool = true;
		System.out.println("boolean, "+bool);
		
		// creating an array
		int [] number = {12, 3, 3, 54, 32, 89, 74};
		System.out.println(Arrays.toString(number));

		// creating an String array for storing string value
		String [] names = {"sathish", "saran", "ragu", "shiva", "siluku"};
		System.out.println(Arrays.toString(names));
		
		// creating a custom objects by using "new" keyword
		String str = new String("Create a new object by using 'new' keyword");
		System.out.println(str);
		
	}
}
