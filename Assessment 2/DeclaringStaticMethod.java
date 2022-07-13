
public class DeclaringStaticMethod {
	
	public void demoMethods() {
		System.out.println("Instance method...");  // we need to create an object for calling the method
	}

	public static void main(String[] args) {
		System.out.println("Static method...Directly invoke by JVM");
		//DeclaringStaticMethod obj = new DeclaringStaticMethod();
		//obj.demoMethods();
	}

}
