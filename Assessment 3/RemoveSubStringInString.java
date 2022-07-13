
public class RemoveSubStringInString {

	public static void main(String[] args) {
		String sentences = "Hai iam sathish";
		String word = "iam";
		
		usingReplaceMethod(sentences, word);
		usingStringBufferReplace(sentences, word);
	}

	private static void usingStringBufferReplace(String sentences, String word) {
		StringBuffer sb = new StringBuffer(sentences);
		System.out.println(sb.replace(4, 7, ""));
	}

	private static void usingReplaceMethod(String sentences, String word) {
		System.out.println(sentences.replaceFirst(word, ""));
	}
}
