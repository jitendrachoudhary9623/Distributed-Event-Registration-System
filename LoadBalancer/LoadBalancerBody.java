package LoadBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Database.DBInteractor;
import Database.DatabaseManager;
import Model.Event;
import Model.EventList;
import Model.RequestBody;
import Model.ResponseBody;
import Model.ServerAddress;
import Utility.EventListGenerator;
import Utility.ServerList;
import Utility.TokenGenerator;

public class LoadBalancerBody implements Runnable{
	LoadBalancer server = null;
	Socket cs = null;
	Socket socket=null;
	ObjectOutputStream oout = null;
	ObjectInputStream oin = null;
	
	ObjectOutputStream o2out = null;
	ObjectInputStream o2in = null;
	Scanner sc=new Scanner(System.in);
	List<ServerAddress> servers=ServerList.getAdress();
	/*Student st = null;
	Operations obj = null;*/
	
	/*Add database as a param*/
	public LoadBalancerBody(LoadBalancer server, Socket cs) {
		super();
		this.server = server;
		//database here
		this.cs = cs;
	}
	
	@Override
	public void run()  {
		// TODO Auto-generated method stub
		try {
	
			oout = new ObjectOutputStream(cs.getOutputStream());
			oin = new ObjectInputStream(cs.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	//Actual Logic

			try {
				RequestBody req=(RequestBody)oin.readObject();
				Random r = new Random();
					
				int  n = r.nextInt(servers.size());
				System.out.println("Forwarding to "+n+" Server Number at port "+servers.get(n).getPort());
		
				while(!serverIsAlive(servers.get(n).getIp(), servers.get(n).getPort())&&servers.size()!=0) {
					System.out.println(servers.get(n).getIp()+" "+servers.get(n).getPort()+" is inactive");
					servers.remove(n);
					n = r.nextInt(servers.size());
				}
				
				socket=new Socket(servers.get(n).getIp(),servers.get(n).getPort());
				o2out = new ObjectOutputStream(socket.getOutputStream());
				o2in = new ObjectInputStream(socket.getInputStream());
				servers.remove(n);
				
				o2out.writeObject(req);
				ResponseBody res=(ResponseBody)o2in.readObject();
				
				oout.writeObject(res);
				if(req.getRequestType()==1 || req.getRequestType()==3) {
				req.setReplica(true);
				req.setToken(res.getToken());
				
				for(ServerAddress s:servers) {
					
					/*if(s.getIp().equals(servers.get(n).getIp())) {
					
					}
					else {*/
						socket=new Socket(s.getIp(),s.getPort());
						o2out = new ObjectOutputStream(socket.getOutputStream());
						o2in = new ObjectInputStream(socket.getInputStream());
						o2out.writeObject(req);
				//	}
				

				}
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		
	}
	
	public static boolean serverIsAlive(String ip,int port) { 
	    try (Socket s = new Socket(ip, port)) {
	        return true;
	    } catch (IOException ex) {
	        /* ignore */
	    }
	    return false;
	}
	
}
