package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	static Connection con = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/XYZ", "root",
					"root");
			
			Statement stmt = con.createStatement();
		      
		      String sql = "CREATE TABLE IF NOT EXISTS EVENT " +
		                   "(id INTEGER not NULL AUTO_INCREMENT, " +
		                   " name VARCHAR(255), " + 
		                   " timestamp VARCHAR(255), " + 
		                   " eventdate VARCHAR(255), " + 
		                   " eventname VARCHAR(255), " + 
		                   " amount INTEGER, " + 
		                   " token VARCHAR(255), " + 

		                   " PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} }