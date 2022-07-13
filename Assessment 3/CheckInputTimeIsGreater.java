import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckInputTimeIsGreater {

	public static void main(String[] args) throws ParseException {

		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
		String systemTime = dateFormat.format(date);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter date and time this format (dd-MM-yyyy HH:mm):");
		Date inputFormat = dateFormat.parse(sc.nextLine());
	
		sc.close();
		if (date.before(inputFormat)) {
			System.out.println("Input time is greater than current time: "+ inputFormat);
		}
		
		else {
			System.out.println("current time is greater than input time: "+ systemTime);
		}
	}

}
