
public class ReplaceCharInString {

	public static void main(String[] args) {
		
		String name1 = "Jova";
		String name2 = "ZAHA";
		
		// call the static methods
		usingReplaceMethod(name1);
		usingReplaceAllMethod(name2);

	}
	
	private static void usingReplaceAllMethod(String name) {
		System.out.println(name.replaceAll("A", "O"));
		
	}

	private static void usingReplaceMethod(String name) {
		System.out.println(name.replace("o", "a"));
	}
	
	

}
