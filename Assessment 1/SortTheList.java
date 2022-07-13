import java.util.Arrays;
import java.util.Scanner;

public class SortTheList {		// using merge sort algorithm to sort the numbers in array
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);            // creating object for scanner class
		int size = sc.nextInt();                        // getting input from the keyword, for array size
		int[] numbers = new int[size];
		for (int i = 0; i < size; i++) {
			System.out.println("Enter a number:");
			numbers[i] = sc.nextInt();
		}
		System.out.println("Before:");
		System.out.println(Arrays.toString(numbers));    // print the sorted number in array
		mergeSort(numbers);
		System.out.println("\nAfter:");
		System.out.println(Arrays.toString(numbers));   // print the sorted number in array
	}
	private static void mergeSort(int[] inputArray) {
		int inputLength = inputArray.length;
		if (inputLength < 2) {
			return;
		}
		int midIndex = inputLength / 2;
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[inputLength - midIndex];
    
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = inputArray[i];
		}
		
		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = inputArray[i];
		}
    
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		merge(inputArray, leftHalf, rightHalf);
	}

  public static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
    int leftSize = leftHalf.length;
    int rightSize = rightHalf.length;
    
    int i = 0, j = 0, k = 0;
    
    while (i < leftSize && j < rightSize) {
      if (leftHalf[i] <= rightHalf[j]) {
        inputArray[k] = leftHalf[i];
        i++;
      }
      else {
        inputArray[k] = rightHalf[j];
        j++;
      }
      k++;
    }
    
    while (i < leftSize) {
      inputArray[k] = leftHalf[i];
      i++;
      k++;
    }
    
    while (j < rightSize) {
      inputArray[k] = rightHalf[j];
      j++;
      k++;
    }
    
  }
}
