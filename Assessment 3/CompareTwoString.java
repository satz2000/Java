public class CompareTwoString {

	private static String str1;
	private static String str2;

	public static void main(String[] args) {
		str1 = "HELLO";
		str2= "hello";
		
		System.out.println("Using CompareTo method");
		usingCompareToMethod();
		
		System.out.println("Using Equals method");
		usingEqualsMethod();
	}			
	
	private static void usingEqualsMethod() {
		// compare two string with equals methods in string class
		System.out.println(str1.equals(str2) ? "Same":"Not Same");
		System.out.println(str1.equalsIgnoreCase(str2) ? "Same":"Not Same");		// compare two string if content is equal returns true
	}

	private static void usingCompareToMethod() {
		// comparing two string with compare methods in string class
		System.out.println(str1.compareTo(str2) ==0? "same": "not same");	// compare each character based on unicode-value
		System.out.println(str1.compareToIgnoreCase(str2) == 0? "same": "not same");
	}

}
