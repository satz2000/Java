// Find the largest number in the given Array
import java.util.Scanner;

public class FindLargestNumber {
	
	// creating static function
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of Array:");
		int size = sc.nextInt();
		
		// creating a array to store the list of values
		int array[] = new int[size];
		for (int i=0; i<size; i++) {
			System.out.println("Enter number "+(i+1)+",");
			array[i] = sc.nextInt();
		}
		// call the method inside main methods
		largestNumber(array);
	}
	
	static void largestNumber(int[] array) {
		
		int maxNumber = array[0];      // stored the 1st value as a max number
		for (int i=0; i<array.length; i++) {
			if (array[i] > maxNumber) {
				maxNumber = array[i];    // if number is greater than max number then stored that number in max
			}
		}
		System.out.print("Maximum number in the Array is, "+maxNumber);
	}
}
