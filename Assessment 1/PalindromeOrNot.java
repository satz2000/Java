// writing a code to check whether the given string is Palindrome or not
import java.util.Scanner;

public class PalindromeOrNot {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter string to check: ");
		String str = sc.next();
		// convert the string into lower case for checking the char
		str = str.toLowerCase();
		// store the length of the string in a length variable
		int length = str.length();
		// using for loop to checking each character is equal from 0th and -1 index value
		for (int i=0; i<length; i++) {
			// checking each character in forward and backward
			if (str.charAt(i) != str.charAt(length-i-1)) {
				System.out.print(str + " is not Palindrome");
				System.exit(0);    // existing from the program
			}
		}
		// if above condition is false then print given string is palindrome
		System.out.print(str + " is Palindrome");
	}

}
