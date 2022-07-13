// writing a code to concatenate two string

public class ConcatTwoString {
	// execution starts from the main method by JVM
	public static void main(String[] args) {
		String str = "ABC";
		
		// using + operator, concat two string
		System.out.println(str+"DEF");
		
		// using inbuilt method "concat"
		System.out.println(str.concat("DEF"));
		
		// create an object for string builder to access the methods of it
		StringBuilder sb = new StringBuilder("ABC");
		sb.append("DEF");    // using the append methods to append the string
		System.out.println(sb);
		
		
	}
}
