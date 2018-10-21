package Model;

import java.io.Serializable;

public class RequestBody implements Serializable{
	int requestType;
	Event event;
	String token;
	public boolean isReplica;
	public int getRequestType() {
		return requestType;
	}
	public RequestBody(int requestType, Event event, String token) {
		super();
		this.requestType = requestType;
		this.event = event;
		this.token = token;
		this.isReplica=false;
	}
	public boolean isReplica() {
		return isReplica;
	}
	public void setReplica(boolean isReplica) {
		this.isReplica = isReplica;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
