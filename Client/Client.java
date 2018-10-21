package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
import Utility.TimeStampGetter;
import Utility.TokenGenerator;

public class Client {
	private static final int REGISTRATION_ID = 1;
	private static final int UPDATE_ID = 2;
	private static final int UPDATE_EVENT_AFTER_CONFIRM = 3;
	static int port=5000;
	static String ip="localhost";
	static Scanner sc = new Scanner(System.in);
	static Socket cs;
	static DataOutputStream dout;
	static DataInputStream din;
	static ObjectOutputStream oout;
	static ObjectInputStream oin;
	public static void init() throws UnknownHostException, IOException {
		 cs = new Socket(ip, port);
		 dout = new DataOutputStream(cs.getOutputStream());
		 din = new DataInputStream(cs.getInputStream());
		 oout = new ObjectOutputStream(cs.getOutputStream());
		 oin = new ObjectInputStream(cs.getInputStream());
	}
	public static void main(String[] args) throws SQLException, UnknownHostException, IOException, ClassNotFoundException {
		while(true)
		{
			
			try {
		init();

		display();
			}catch(EOFException e) {
				
			}
		}
	}
	
	static void display() throws UnknownHostException, IOException, ClassNotFoundException {
		System.out.println("Menu\n1.Register For Event\n2.Update your information\n3.Exit");
		switch(sc.nextInt()) {
		case 1:
			//Registeration
			//registeration();
			break;
		case 2:
			//Updation
			updation();
			break;
		case 3:
			//Exit
			break;
		}
		cs.close();
	}

	
	public static Event checkEvent(String token) throws UnknownHostException, IOException, ClassNotFoundException {
		init();
		RequestBody req=new RequestBody(UPDATE_ID,null,token);
		oout.writeObject(req);
		ResponseBody res=(ResponseBody)oin.readObject();
		Event e=res.getEvent();
		return e;
		
	}
	
	public static String updateEvents(Event e,String token) throws IOException, ClassNotFoundException {
		init();
		RequestBody req=new RequestBody(UPDATE_EVENT_AFTER_CONFIRM, e, token);
		oout.writeObject(req);
	/*	dout.writeUTF(token);
		oout.writeObject(e);*/
		
		ResponseBody res=(ResponseBody) oin.readObject();
		
		return res.getMessage();
	}
	private static void updation() throws IOException, ClassNotFoundException {
		// TODO Auto1-generated method stub
		
		
		System.out.println("Enter token id for updating your event details");
		String token=sc.next();
		/*dout.writeInt(UPDATE_ID);
		dout.writeUTF(token);*/
		
		RequestBody req=new RequestBody(UPDATE_ID,null,token);
		oout.writeObject(req);
		ResponseBody res=(ResponseBody)oin.readObject();
		Event e=res.getEvent();
		if(e!=null) {
		System.out.println(e.toString());
		System.out.println("Do you want to update ?");
		switch(sc.next()) {
		case "y":
			init();
			System.out.println("If you want to update name please type the new name else type -1");
			String nn=sc.next();
			if(!nn.equals("-1")) {
				e.setAttendee(nn);
			}
			//dout.writeInt(UPDATE_EVENT_AFTER_CONFIRM);
			req=new RequestBody(UPDATE_EVENT_AFTER_CONFIRM, e, token);
			oout.writeObject(req);
		/*	dout.writeUTF(token);
			oout.writeObject(e);*/
			
			res=(ResponseBody) oin.readObject();
			System.out.println(res.getMessage());
			break;
		case "n":
			System.out.println("Thank you for using our service");
			break;
		}
	}else {
		System.out.println("Invalid Token");
	}
	}

	public static String registeration(String name1,int event_no) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		init();
		RequestBody req;
		
		//System.out.println("Enter Your Name");
		String name=name1;//sc.next();
		List<EventList> events=EventListGenerator.getEvents();
		/*int i=1;
		for(EventList e:events) {
			System.out.println(i+" - "+e.getEventname()+" - "+e.getFees());
			i++;
		}
		System.out.println("Enter event number");
		*/String eventname=null;
		switch(event_no/*sc.nextInt()*/) {
		case 1:
			eventname=events.get(0).getEventname();
			break;
		case 2:
			eventname=events.get(1).getEventname();

			break;
			
		case 3:
			eventname=events.get(2).getEventname();

			break;
		}
		
		Event e1=new Event(name,TimeStampGetter.getTimestamp(),TimeStampGetter.getDate(),eventname);
		
		
		req=new RequestBody(REGISTRATION_ID, e1, null);
		oout.writeObject(req);
		System.out.println("Waiting for respoinse");
		ResponseBody res=(ResponseBody) oin.readObject();
		String token=res.getToken();
		System.out.println("Here is your Token Please don't loose it "+res.getToken());
		return token;

	}
}
