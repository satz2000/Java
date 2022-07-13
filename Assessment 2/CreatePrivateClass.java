import java.util.Scanner;

public class CreatePrivateClass {
	static String userID = "sathishpbs55";
	
	private final static class InnerClass {
		static long password = 9876543210L;
		static final int key = 5555;
		
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your unique key: ");
			int inputID = sc.nextInt();
			// here we can create an object for inner directly, because we create as static class
			//CreatePrivateClass.InnerClass inner = new CreatePrivateClass.InnerClass();
			showPassword(inputID);
		}
		
		private static void showPassword(int inputID) {
			// creating an object for outer class
			CreatePrivateClass outer = new CreatePrivateClass();
			
			if (inputID == key) {
				System.out.println("User ID: "+ outer.userID);
				System.out.println("Password: "+password);
			}
			else {
				// creating an object for outer class
				System.out.println("User ID: "+ outer.userID);
			}
		}
	}
}
