package Model;

public class ServerAddress {

	String ip;
	int port;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public ServerAddress(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	public ServerAddress() {
		
	}
	public void setPort(int port) {
		this.port = port;
	}
}
