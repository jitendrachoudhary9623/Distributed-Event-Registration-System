package Model;

import java.io.Serializable;

public class ResponseBody implements Serializable{
	String token;
	String message;
	public ResponseBody(String message) {
		super();
		this.message = message;
	}
	public ResponseBody(String token, String message, Event event) {
		super();
		this.token = token;
		this.message = message;
		this.event = event;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseBody(String token, Event event) {
		super();
		this.token = token;
		this.event = event;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	Event event;
}
