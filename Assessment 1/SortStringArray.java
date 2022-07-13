import java.util.Arrays;
import java.util.Scanner;    // getting input from the user

public class SortStringArray {
		
	public static void sortStringArray(String[] arr, int size) {
			String temp;
			
			for (int i=0; i<size; i++) {
				for (int j=1; j<size-i; j++) {
					if (arr[j-1].compareToIgnoreCase(arr[j])>0) {
						temp = arr[j-1];
						arr[j-1] = arr[j];
						arr[j] = temp;
					}
				}
			}
		}

	public static void main(String[] args) {
		// creating an object for Scanner modules
		Scanner sc = new Scanner(System.in);
		// getting the size of array from the user
		System.out.println("Enter the size of Array: ");
		int size = sc.nextInt();
		// creating an array to store the string value getting from the user
		String[] arr = new String[size];
		for (int i=0; i<size; i++) {
			System.out.println("Enter the string "+ (i+1)+ " :");
			arr[i] = sc.next();
		}
		System.out.println("---------------Before Sorting---------------");
		System.out.println(Arrays.toString(arr));
		
		sortStringArray(arr, size);
		System.out.println("---------------After Sorting---------------");
		System.out.println(Arrays.toString(arr));
	
	}
}
