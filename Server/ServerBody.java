package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Database.DBInteractor;
import Database.DatabaseManager;
import Model.Event;
import Model.EventList;
import Model.RequestBody;
import Model.ResponseBody;
import Utility.EventListGenerator;
import Utility.TokenGenerator;

public class ServerBody implements Runnable{
	Server server = null;
	Socket cs = null;
	DataOutputStream dout = null;
	DataInputStream din = null;
	ObjectOutputStream oout = null;
	ObjectInputStream oin = null;
	
	DataOutputStream sdout = null;
	DataInputStream sdin = null;
	ObjectOutputStream soout = null;
	ObjectInputStream soin = null;
	
	Scanner sc=new Scanner(System.in);
	Socket socket=null;
	/*Student st = null;
	Operations obj = null;*/
	
	/*Add database as a param*/
	public ServerBody(Server server, Socket cs) {
		super();
		this.server = server;
		//database here
		this.cs = cs;
	}
	
	@Override
	public void run()  {
		// TODO Auto-generated method stub
		try {
			dout = new DataOutputStream(cs.getOutputStream());
			din = new DataInputStream(cs.getInputStream());
			oout = new ObjectOutputStream(cs.getOutputStream());
			oin = new ObjectInputStream(cs.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//Actual Logic

			try {
				
					RequestBody req=(RequestBody)oin.readObject();
					int id=req.getRequestType();
					//sdout.writeInt(id);
					System.out.println("Recieved "+id);
					switch(id) {
					case 1:
						Event e1=req.getEvent();
						
						
						RequestRegisteration(e1,req.isReplica(),req.getToken());
						break;
					case 2:
						RequestUpdate(req.getToken());

					break;
					case 3:
						RequestUpdateAfterCheck(req.getToken(),req.getEvent());

						break;
					}
					
			} catch(Exception e) {
				System.out.println(e);
			}
		
	}
	private void RequestUpdateAfterCheck(String token , Event e) throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		
	/*	String token=din.readUTF();
		Event e=(Event)oin.readObject();*/
		int check=DBInteractor.updateEvent(e, token);
		ResponseBody res;
		if(check==1) {
			res=new ResponseBody("Update Successfull");
		}
		else {
			res=new ResponseBody("Unknown Operation Occured");

		}
		
		oout.writeObject(res);
	}

	private boolean RequestUpdate(String Token) throws IOException, SQLException {
		// TODO Auto-generated method stub
		//String token=din.readUTF();
		
		
		Event e=DBInteractor.checkEditEvent(Token);
		ResponseBody res=new ResponseBody(null, e);
		oout.writeObject(res);

		if(e!=null) {
		return true;
		}
		return false;
	}

	private void RequestRegisteration(Event e1,boolean isReplica,String t1) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Recieved from client "+e1.getAttendee()+"\n"+e1.getEventname());
		String eventname=e1.getEventname();
		int amount=0;
		List<EventList> list=EventListGenerator.getEvents();
		for(EventList a:list) {
			if(a.getEventname().equals(eventname)) {
				amount=a.getFees();
				break;
			}
		}
		String Token;
		System.out.println("t1 "+t1);
		if(t1==null)
		  Token=TokenGenerator.getToken();
		else
			Token=t1;
		int i=new DBInteractor().registerForEvent(e1, Token, amount);
		System.out.println(i);
		Event e=new DBInteractor().checkEditEvent(Token);
		if(e!=null) {
			System.out.println(e.getAttendee()+" "+e.getId()+" "+e.getAmount());
			
		}
		else {
			System.out.println("No token");
		}	
		ResponseBody res=new ResponseBody(Token, null);
		
		if(!isReplica)
			oout.writeObject(res);
	}

}
