package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	static int port=8004;  //5000
	ServerSocket ss = null;
	Socket cs = null;
	//Operations obj = null;
	ExecutorService pool = null;
	public Server(int port){
		this.port = port;
		//obj = new Operations();
		pool = Executors.newFixedThreadPool(5);
	}
	public void startServer(){
		try {
			//creating one server socket
			ss = new ServerSocket(port);
			//for accepting multiple clients
			while(true){
				System.out.println("Welcome I am A server and port number is "+port);
				System.out.println("Server waiting for client....");
				cs = ss.accept();
				System.out.println("Got one client.Creating thread for this client...");
				ServerBody runnable = new ServerBody(this, cs);
				//assigning thread to pool
				pool.execute(runnable);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server(port).startServer();
	}
}
