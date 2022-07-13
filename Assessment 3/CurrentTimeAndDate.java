import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTimeAndDate {

	public static void main(String[] args) {
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(time.format(date));
	}

}
