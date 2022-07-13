// override toString method
public class OverrideToStringMethod {
	protected String name;	
	protected int age;

	public  OverrideToStringMethod(String name, int age) {	// creating an own constructor,  passing two Arguments
		this.name = name;
		this.age = age;
	}
	
	// override toString method for own customization
	@Override
	public String toString() {
		return "Name: "+ name + ", " + "Age: " + age;
	}
	
	public static void main(String[] args) {
		OverrideToStringMethod obj1 = new OverrideToStringMethod("Sathish", 21);
		OverrideToStringMethod obj2 = new OverrideToStringMethod("Toxin", 21);
		
		System.out.println(obj1);			// only print the object but its call directly to toString methods automatically
		System.out.println(obj2.toString());	// Also call toString method also its prints same
	}

}
