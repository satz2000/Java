
import java.io.*;
import java.util.*;

class SearchingThread extends Thread{
	String[] files;
	List<String> words;
	String threadNumber;
	
	public void searchFiles(String threadNo, String[] files, List<String> words) {
		this.files = files;
		this.words = words;
		this.threadNumber = threadNo;
	}
	
	public void run(){
		List<String> inputStringArray = new ArrayList<>();
		Scanner sc = null;
		for(String s: files) {
			try {
				sc = new Scanner(new File(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while(sc.hasNext()) {
				inputStringArray.add(sc.next());
			}
			for(String s1: words) {
				if(inputStringArray.contains(s1)) {
					System.out.println(s1+ " - Match, "+ threadNumber);
				}else {
					System.out.println(s1+" - No match, "+threadNumber);
				}
			}
		}
		System.out.println(System.currentTimeMillis());
	}
}

public class SearchWordUsingDifferentThread {

	public static void main(String[] args) throws FileNotFoundException{
		String[] files1 = {"C:/Users/sathi/Desktop/sample-input-search-file-1.txt"};
		
		String[] files2 = {"C:/Users/sathi/Desktop/sample-input-search-file-2.txt"};
		
		String searchFiles = "C:/Users/sathi/Desktop/sample-search-tokenfile.txt";
		
		Scanner sc_file = new Scanner(new File(searchFiles));
		List<String> words = new ArrayList<>();
		
		while(sc_file.hasNext()) {
			words.add(sc_file.next());
		}
		sc_file.close();
		
		SearchingThread t1 = new SearchingThread();
		t1.searchFiles("T1",files1, words);
		t1.start();
		
		SearchingThread t2 = new SearchingThread();
		t2.searchFiles("T2", files2, words);
		t2.start();
	}
}
