import java.util.StringTokenizer;

public class TokenizingTheString {
	
	public static void main(String[] args) {
		StringTokenizer tokenize = new StringTokenizer("Hello/world/iam/sathish");
		while(tokenize.hasMoreTokens()) {
			System.out.println(tokenize.nextToken("/"));
		}
	}

}
