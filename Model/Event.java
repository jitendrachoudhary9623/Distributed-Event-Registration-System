package Model;

import java.io.Serializable;

public class Event implements Serializable{
	/*"CREATE TABLE IF NOT EXISTS EVENT " +
    "(id INTEGER not NULL AUTO_INCREMENT, " +
    " name VARCHAR(255), " + 
    " timestamp VARCHAR(255), " + 
    " eventdate VARCHAR(255), " + 
    " eventname VARCHAR(255), " + 
    " amount INTEGER, " + 
    " token VARCHAR(255), " + 

    " PRIMARY KEY ( id ))"; */
	
	String Attendee;
	String timestamp;
	String date;
	String eventname;
	int id;
	int amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Event(String attendee, String timestamp, String date, String eventname) {
		super();
		Attendee = attendee;
		this.timestamp = timestamp;
		this.date = date;
		this.eventname = eventname;
	}
	public String getAttendee() {
		return Attendee;
	}
	public void setAttendee(String attendee) {
		Attendee = attendee;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Attendee "+this.getAttendee()+"\nEvent Name "+this.getEventname()+"\nEvent Date "+this.getDate()+"\nEvent Amount "+this.getAmount();
	}
}
