// copying a string to another
import java.util.Scanner;

public class CopyingStringToAnother {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a string value to copy: ");
		String str = sc.next();
		
		StringBuffer sbf = new StringBuffer(str);
		System.out.println(sbf);
		
		StringBuilder sb = new StringBuilder(str);
		System.out.println(sb);
	
		String str2 = String.copyValueOf(str.toCharArray());
		System.out.println(str2);
		
		String str3 = str;
		System.out.println(str3);

	}
}

