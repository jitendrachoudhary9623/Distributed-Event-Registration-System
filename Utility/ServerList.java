package Utility;

import java.util.ArrayList;
import java.util.List;

import Model.ServerAddress;

public class ServerList {
	
	public static List<ServerAddress> getAdress(){
		
		List<ServerAddress> server=new ArrayList<>();
		//server.add(new ServerAddress("localhost",8000));

		server.add(new ServerAddress("localhost",8002));

		server.add(new ServerAddress("localhost",8003));
		server.add(new ServerAddress("localhost",8004));
		server.add(new ServerAddress("localhost",8006));

		server.add(new ServerAddress("localhost",8001));

		return server;
		
	}
}
