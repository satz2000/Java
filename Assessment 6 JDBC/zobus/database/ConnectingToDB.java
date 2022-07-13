package zobus.database;
import java.sql.*;

public class ConnectingToDB {
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	public static Connection connectDB (){
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:8888/database","postgres", "1234");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public static ResultSet getDataFromDB(String query) {
		connection = connectDB();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return resultSet;
	}

	public static PreparedStatement insertDataToDB(String query){
		try {
			connection = connectDB();
			preparedStatement = connection.prepareStatement(query);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}

	public static PreparedStatement updateDataToDB(String query){
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}

	public static PreparedStatement deleteDataFromDB(String query){
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}
}

