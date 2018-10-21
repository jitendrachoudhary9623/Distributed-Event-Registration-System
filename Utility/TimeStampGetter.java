package Utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampGetter {

	public static String getTimestamp() {
		
		return String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
	}
	
	public static String getDate() {
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("dd/MM/yyyy");
return ft.format(dNow);
	}
}
