import java.util.StringTokenizer;

public class SplitStringwithDelimiter {

	public static void main(String[] args) {
		String phoneNumber = "984-292-2606";
		
		usingStringSplitMethod(phoneNumber);
		usingTokenizerMethods(phoneNumber);
	}

	private static void usingStringSplitMethod(String phoneNumber) {
		System.out.println("Using String class Split() method");
		String subString[] = phoneNumber.split("-");	// we can use regular expression
		for(String s: subString) {		// using for-each loop to print array string after splitting
			System.out.println(s);
		}
	}
	
	private static void usingTokenizerMethods(String phoneNumber) {
		System.out.println("Using StringTokenizer class for spliting");
		StringTokenizer tokens = new StringTokenizer(phoneNumber);
		while(tokens.hasMoreTokens()) {
			System.out.println(tokens.nextToken("-"));		// we can use one delimiter at a time
		}
		
	}
}
