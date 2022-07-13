
public class RemoveDigitsInString {

	public static void main(String[] args) {
		
		String userID = "sathishpbs55";
		
		usingReplaceMethod(userID); 	// calling replace methods
		usingCharAtMethod(userID);
	}
	
	static void usingReplaceMethod(String userID) {
		userID = userID.replaceAll("\\d", "");		// replacing with regex value of digits (\\d)
		System.out.println(userID);
	}
	
	static void usingCharAtMethod(String userID) {
		
		String result = "";
		for (int i=0; i<userID.length(); i++) {
			if (!Character.isDigit(userID.charAt(i))) {
				result += userID.charAt(i);
			}
		}
		System.out.println(result);
	}

}
