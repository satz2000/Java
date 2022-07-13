package sathish;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class SearchingTokenInFile extends Thread {
	int threadCount;
	int mod;
	ArrayList<String> listOfWords;
	ArrayList<String> searchTokens;
	// create a constructor to pass the value
	public SearchingTokenInFile(int threadCount, int mod, ArrayList<String> listOfWords, ArrayList<String> searchTokens) {
		this.mod = mod;
		this.threadCount = threadCount;
		this.listOfWords = listOfWords;
		this.searchTokens = searchTokens;

	}

	@Override
	public void run() {
		ArrayList<String> splitTokens = new ArrayList<>();
		for(int i=0; i<searchTokens.size(); i++){
			if(i%threadCount == mod){
				splitTokens.add(searchTokens.get(i));
			}
		}

		for(String s: splitTokens) {
			if (listOfWords.contains(s)) {
				System.out.println(s + " - Match");
			} else {
				System.out.println(s + " - No match");
			}
		}
	}
}
public class MultiThreading{
	// storing list of words in it
	static ArrayList<String> inputFileList = new ArrayList<>();
	static ArrayList<String> searchTokensList = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final long start = System.currentTimeMillis();
		
		System.out.print("Enter Thread count: "); 
		int threadCount = sc.nextInt();
		sc.close();
		
		// input files path
		String[] files = {"C:/Users/sathi/OneDrive/Desktop/sample-input-search-file-1.txt",
				"C:/Users/sathi/OneDrive/Desktop/sample-input-search-file-2.txt"};

		String searchFiles = "C:/Users/sathi/OneDrive/Desktop/sample-search-tokenfile.txt";

		System.out.println("No of Threads: " + threadCount);
		System.out.println("Sample no of Input: "+files.length);

		inputFileReader(files);         // processing the input file
		searchFileReader(searchFiles);  // processing the search file

		for (int i=0; i<threadCount; i++){
			new SearchingTokenInFile(threadCount, i, inputFileList, searchTokensList).start();
		}

		final long end = System.currentTimeMillis();
		
		// after the execution is done, then print this methods
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			NumberFormat formatter = new DecimalFormat("#0.00");
			System.out.print("Total time taken: " + formatter.format((end - start) / 1000d) + " sec");
		}));
	}
	
	private static void inputFileReader(String[] files) {
		try {
			for (String file: files){
				Scanner sc = new Scanner(new File(file));
				while (sc.hasNext()) {
					inputFileList.add(sc.next());
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void searchFileReader(String file) {
		try {
			Scanner sc = new Scanner(new File(file));
			while(sc.hasNext()) {
				searchTokensList.add(sc.next());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}