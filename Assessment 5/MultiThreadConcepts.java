import java.io.*;
import java.util.*;

public class MultiThreadConcepts{

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc1 = new Scanner(new File("C:/Users/sathi/Desktop/sample-input-search-file-1.txt"));
		Scanner sc2 = new Scanner(new File("C:/Users/sathi/Desktop/sample-input-search-file-2.txt"));
		Scanner sc3 = new Scanner(new File("C:/Users/sathi/Desktop/sample-search-tokenfile.txt"));
		
		Set<String> arr1 = new HashSet<>();
		Set<String> arr2 = new HashSet<>();
		Set<String> arr3 = new HashSet<>();
		
		while(sc1.hasNext()) {
			arr1.add(sc1.next());
		}
		
		while(sc2.hasNext()) {
			arr2.add(sc2.next());
		}
		
		while(sc3.hasNext()) {
			arr3.add(sc3.next());
		}
		
		printOutput(arr1, arr2, arr3);
	}
	
	static void checkinInputFile(Set<String> arrList, Set<String> arrCheckWord) {
		for(String s: arrCheckWord) {
			if(arrList.contains(s)) {
				System.out.println(s+" - match");
			}else {
				System.out.println(s+" - No match");
			}
		}
	}
	
	public static void printOutput(Set<String> arr1, Set<String> arr2, Set<String> arr3) {
		System.out.println("Checking in InputFile 1.....");
		checkinInputFile(arr1, arr3);
		System.out.println("-----------------------------------------------");
		System.out.println("Checking in InputFile 2.....");
		checkinInputFile(arr2, arr3);
	}
}
