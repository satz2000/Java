
public class CompareTwoStringSame {

	public static void main(String[] args) {
		String str1 = "Java";
		String str2 = "Java";
		CheckingIsBoolean(str1,  str2);
		
	}
	
	static boolean isEqualIgnore(String str1,String str2) {
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		int val = str1.compareTo(str2);
		
		if (val == 0) {
			return true;
			}
		
		else {
			return false;
		}
	}
	
	public static void CheckingIsBoolean(String str1, String str2) {
		boolean isEqual = isEqualIgnore(str1, str2);
		
		if (isEqual) {
			System.out.println("same string");
		}
		
		else {
			System.out.println("Not same");
		}
	}

}
