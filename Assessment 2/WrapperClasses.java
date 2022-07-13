// wrapper also does same thing like primitive data types, but its slower because its class, its used in framework and something
public class WrapperClasses {

	public static void main(String[] args) {
		
		Float f = 2.5F;
		System.out.println(f);
		
		// converting float into integer by using wrapper class
		int i = f.intValue();
		System.out.println(i);
	}
}
