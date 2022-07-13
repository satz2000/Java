import com.sathish1.ClassAccessDifferentPackage;
// If you not specify Any access modifier, it will be default access
class Default {
	private String name;
	public String setName(String name) {
		return this.name = name;		// this keyword refers to current object
	}
	public String getName () {
		return this.name;
	}
}


public class ClassAccessModifier extends ClassAccessDifferentPackage {
	public static void main(String[] args) {
		
		//create an object for default class to access methods of that class
		Default obj = new Default();
		
		// call the setName methods to pass a string
		obj.setName("Sathish");
		
		// call protected methods
		callProtectedMethod();
		
		// print the getName methods
		System.out.println(obj.getName());
		printComments();		// call the private method to print 'bye'
	}
	
	public static void callProtectedMethod() {		// call protected methods inside a method of different packages
		ClassAccessDifferentPackage.protectedClass();
	}
	
	private static void printComments() {		// call this  private method within the class
		System.out.println("Bye..!");
	}
}
