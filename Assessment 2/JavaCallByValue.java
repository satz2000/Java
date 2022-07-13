// In java, call by value only,
public class JavaCallByValue {

	public static void main(String[] args) {

		String str = "ABC";
		System.out.println("-----Before change-----");
		System.out.println(str);
		
		// calling the method inside a main methods
		System.out.println("-----After change-----");
		change(str);
		System.out.println(str);
	}
	public static void change(String str) {
		str = str+"DEF";     // copy of str value and passing into it
						 //so change in value doesn't affect the original value
	}
}
