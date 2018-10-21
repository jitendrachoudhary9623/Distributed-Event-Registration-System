package LoadBalancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadBalancer {
	static int port=5000; //8002
	ServerSocket ss = null;
	Socket cs = null;
	//Operations obj = null;
	ExecutorService pool = null;
	public LoadBalancer(int port){
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
				System.out.println("Load Balancert....");
				cs = ss.accept();
				System.out.println("Got one client.Forwarding this client...");
				LoadBalancerBody runnable = new LoadBalancerBody(this, cs);
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
		new LoadBalancer(port).startServer();
	}
}
