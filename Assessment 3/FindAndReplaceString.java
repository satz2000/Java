
public class FindAndReplaceString {

	public static void main(String[] args) {
		String str1 = "ababbab";		// sentences
		String str2 = "aba";			// finding word
		String str3 = "cdc";			// to replace the str2 value in str1 by str3 value
		
		usingReplaceAllMethod(str1, str2, str3);
	}

	private static void usingReplaceAllMethod(String str1, String str2, String str3) {
		if (str1.contains(str2)) {
			System.out.println(str1.replaceAll(str2, str3));
		}
		else {
			System.out.println("Given string is not present. So, we are not able to replace it..!");
		}
	}

}
