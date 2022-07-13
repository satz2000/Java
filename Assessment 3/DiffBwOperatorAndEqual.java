
public class DiffBwOperatorAndEqual {

	public static void main(String[] args) {
		String str = "Halo";	// create an object and pass string into it
		String str1 = new String("Halo");
		
		
		System.out.println("Calling == operator:");
		equalOperator(str, str1);
		
		System.out.println("Calling equal methods:");
		equalMethod(str,str1);
	}
	
	private static void equalMethod(String str, String str1) {
		System.out.println(str.equals(str1)? "Same value":"Same value but checking reference of the value");
	}

	private static void equalOperator(String str, String str1) {
		System.out.println(str==str1? "Same value":"Same value but checking reference of the value");
	}

}
