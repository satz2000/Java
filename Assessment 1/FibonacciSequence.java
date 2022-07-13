import java.util.Scanner;
public class FibonacciSequence {
	static long[] fibCache;			// creating a static array as a long data type for storing the value of each fib number
	// creating a static methods
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number, ");
		int n = sc.nextInt();
		fibCache = new long[n +1];     // if u want to find fib of 2 you need to store the 3 value()
		//call the printValue methods
		printValue(n);
	}
	public  static void printValue(int n) {
		for (int i=0; i<=n; i++) {
			System.out.println(fib(i));
		}
	}
	public static long fib(int n) {
		if (n<=1) {
			return n;
		}
		if (fibCache[n] != 0) {
			return fibCache[n];
		}
		long fibNumber = (fib(n-1) + fib(n-2)); 	// using recursion method by call itself inside the method and stored into variable
		fibCache[n] = fibNumber;			// assign that value into fibCache array by using n as index to assign in it 
		return fibNumber;		// return the value
		
	}
}
