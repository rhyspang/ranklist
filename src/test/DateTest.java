package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		String f1 = "yyyy_MM_dd HH:mm:ss";
		Date d1 = new Date();
		String format = "HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat sdf1 = new SimpleDateFormat(f1);
		Date dd = sdf1.parse("2016_8_11 9:00:00");
		
		System.out.println(sdf1.format(d1));
		System.out.println(new Date(d1.getTime() - dd.getTime() -8*60*60*1000 ).getHours()  );
		
	}

}
