
public class FindLengthOfString {

	public static void main(String[] args) {
		String str = "dsfgkjbnnbbs";
		// converting the string into array
		char[] charToArray = str.toCharArray();
		// initialize the iteration value
		int i = 0;
		for (char c: charToArray) {
			i++;
		}
		// print the iteration value, because its count of char in string after converting string into array
		System.out.println("Length of the String is "+ i);
		
		
	}

}
