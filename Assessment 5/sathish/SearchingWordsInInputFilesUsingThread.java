package sathish;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

class SearchingWordsUsingMultiThread extends Thread {
	int threadCount;
	int modValue;
	List<String> inputWordsInList = new ArrayList<>();
	List<String> searchingWords = new ArrayList<>();
	
	public SearchingWordsUsingMultiThread(int threadCount, int modValue, List<String> inputWordsInList, List<String> searchingWords) {
		this.threadCount = threadCount;
		this.modValue = modValue;
		this.inputWordsInList = inputWordsInList;
		this.searchingWords = searchingWords;
	}
	
	@Override
	public void run() {
		List<String> listOfIndividualWords = new ArrayList<>();
		for(int i=0; i<inputWordsInList.size(); i++) {
			if(i%threadCount == modValue) {
				listOfIndividualWords.add(inputWordsInList.get(i));
			}
		}
		
		for(String word: searchingWords) {
            if (listOfIndividualWords.contains(word)) {
                SearchingWordsInInputFilesUsingThread.matchedWords.add(word);
            } else {
                SearchingWordsInInputFilesUsingThread.notMatchedWords.add(word);
            }
        }
	}
}

public class SearchingWordsInInputFilesUsingThread {
	static List<String> inputWordsInList = new ArrayList<>();
	static List<String> searchWordsInList = new ArrayList<>();
	
	static Set<String> matchedWords = new HashSet<>();
	static Set<String> notMatchedWords = new HashSet<>();

	public static void main(String[] args) {
        // start time
		Long start = System.currentTimeMillis();

        // files path
		String inputFilePath = "C:/Users/sathi/OneDrive/Desktop/sample-input-search-file-1.txt";
		String searchFilePath = "C:/Users/sathi/OneDrive/Desktop/sample-search-tokenfile.txt";

        // getting input from the user for thread count
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of thread: ");
		int threadCount = sc.nextInt();
		sc.close();

        // calling method to storing the values in the ArrayList
		splitInputTextFileIntoList(inputFilePath);
		splitSearchFileIntoList(searchFilePath);

        // based on thread count it creates number of threads
		for(int i=0; i<threadCount; i++) {
			new SearchingWordsUsingMultiThread(threadCount, i, inputWordsInList, searchWordsInList).start();
		}
        // end time
        Long end = System.currentTimeMillis();

        // using shutdownHook methods to stay alive jvm until this function is executed
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("No of Thread: "+threadCount);
				notMatchedWords.removeAll(matchedWords);
				for(String words: matchedWords) {
					System.out.println(words + " - Match");
				}
				for(String words: notMatchedWords) {
					System.out.println(words + " - No Match");
				}
                NumberFormat formater = new DecimalFormat("#0.00");
                System.out.println("Time Taken: "+ formater.format((end-start)/1000d)+ " sec");
			}
		});
	}

	private static void splitSearchFileIntoList(String searchFilePath) {
		try {
			Scanner sc = new Scanner(new File(searchFilePath));
			while(sc.hasNext()) {
				searchWordsInList.add(sc.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void splitInputTextFileIntoList(String inputFilePath) {
		try {
			Scanner sc = new Scanner(new File(inputFilePath));
			while(sc.hasNext()) {
                inputWordsInList.add(sc.next());
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
}
