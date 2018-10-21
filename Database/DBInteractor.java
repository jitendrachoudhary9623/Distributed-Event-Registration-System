package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Event;

public class DBInteractor {
	
	public static int registerForEvent(Event e,String token,int amount) throws SQLException {
		/*
		 * "CREATE TABLE IF NOT EXISTS EVENT " +
		                   "(id INTEGER not NULL AUTO_INCREMENT, " +
		                   " name VARCHAR(255), " + 
		                   " timestamp VARCHAR(255), " + 
		                   " eventdate VARCHAR(255), " + 
		                   " eventname VARCHAR(255), " + 
		                   " amount INTEGER, " + 
		                   " token VARCHAR(255), " + 

		                   " PRIMARY KEY ( id ))"; 
		 */
			Connection con=DatabaseManager.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into EVENT(name, timestamp, eventdate,eventname,amount,token) values(?,?,?,?,?,?)");
			ps.setString(1, e.getAttendee());
			ps.setString(2, e.getTimestamp());
			ps.setString(3, e.getDate());
			ps.setString(4, e.getEventname());
			ps.setInt(5, amount);
			ps.setString(6, token);

			int rowsInserted = ps.executeUpdate();
			con.close();
		return rowsInserted;
	}

	public static Event checkEditEvent(String token) throws SQLException {
		Connection con=DatabaseManager.getConnection();
		Event e=null;
		PreparedStatement ps = con.prepareStatement(
				"Select * from EVENT where token = ?");
				ps.setString(1, token);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					/*String attendee, String timestamp, String date, String eventname
					name, timestamp, eventdate,eventname,amount,token*/
				e = new Event(rs.getString("name"),
				rs.getString("timestamp"), rs.getString("eventdate"),rs.getString("eventname"));
				e.setId(rs.getInt("id"));
				e.setAmount(rs.getInt("amount"));
				}
				con.close();
		return e;
		
	}
	
	public static int updateEvent(Event e,String token) throws SQLException {
		Connection con=DatabaseManager.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"Update EVENT set name = ? where token = ?");
		        ps.setString(1, e.getAttendee());
				ps.setString(2, token);
				int rowUpdated = ps.executeUpdate();
				con.close();
		return rowUpdated;
		
	}
}
