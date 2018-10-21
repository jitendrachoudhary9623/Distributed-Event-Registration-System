package Utility;

import java.util.ArrayList;
import java.util.List;

import Model.EventList;

public class EventListGenerator {

	static List<EventList> el;
	
	public static List getEvents() {
		el=new ArrayList<EventList>();
		EventList a=new EventList();
		
		a.setEventname("Jhankar");
		a.setFees(500);
		el.add(a);
		a=new EventList();
		a.setEventname("Alpha Quest");
		a.setFees(100);
		el.add(a);
		a=new EventList();
		a.setEventname("Digital Rennissance");
		a.setFees(300);
		el.add(a);
		
		return el;
	
		
	}

	
}
