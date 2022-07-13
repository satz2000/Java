import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class OccuranceOfSubString {

	public static void main(String[] args) {
		String sentences = "Haia iam sathish iam from kdm";
		String word = "iam";
		
		usingIndexOfMethod(sentences, word);
		usingSplitMethod(sentences, word);
		usingPatternMatcher(sentences, word);
		
	}

	private static void usingPatternMatcher(String sentences, String word) {
		Pattern pattern = Pattern.compile(word);
		Matcher matcher = pattern.matcher(sentences);
		
		int count = 0;
		
		while (matcher.find()) {
			//System.out.println(matcher.start());
			count++;
			//System.out.println(matcher.end());
		}
		System.out.println("Using Regex Pattern Matcher method, Occurance is, "+count);
		
	}

	private static void usingSplitMethod(String sentences, String word) {
		// using split method
		int i =0;
		System.out.println("Using Split method: Occurance of substring is, " + (sentences.split(word, i).length-1));
		
	}

	private static void usingIndexOfMethod(String sentences, String word) {
		// using indexOf methods
		int startIndex = 0;
		int count = 0;
		while (startIndex != -1) {
			startIndex = sentences.indexOf(word, startIndex);
			
			if (startIndex != -1) {
				count++;
				startIndex += word.length();
			}
		}
		System.out.println("Using IndexOf method: Occurance of substring is, "+count);
	}
}
