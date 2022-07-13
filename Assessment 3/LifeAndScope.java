
public class LifeAndScope {
	static String name = "Rohit";		// scope of this variable belongs to this class
	
	public static void main(String[] args) {
		int age = 21;					// scope of this variable belongs to this method
		
		System.out.println(age);
		System.out.println(name);
		FindScopeOfVariable();
	}
	
	public static void FindScopeOfVariable() {
		System.out.println(name);		
	}
}
