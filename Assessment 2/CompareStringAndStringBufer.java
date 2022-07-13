public class CompareStringAndStringBufer {

	public static void main(String[] args) {
		long startTimeString = System.currentTimeMillis();
		// call the string methods
		concatWithString();
		long startTimeStringBuffer = System.currentTimeMillis();
		// call the string-buffer methods
		concatWithStringBuffer();
		long timeTaken = startTimeString - startTimeStringBuffer;
		System.out.println("For 1000 Iteration: String methods takes " + (-timeTaken) + "ms higher than StringBuffer");
	}
	public static void concatWithString() {
		String str1 = new String("Java");
		for (int i=0; i<1000; i++) {
			str1 = str1.concat(" Learning");
		}
	}
	
	public static void concatWithStringBuffer() {
		StringBuffer str2 = new StringBuffer("Java");
		for (int i=0; i<1000; i++) {
			str2 = str2.append(" Learning");
		}
	}
}
