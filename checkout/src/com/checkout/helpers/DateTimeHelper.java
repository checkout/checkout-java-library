package com.checkout.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

	 public static String GetUtcNow()
     {
         return FormatAsUtc(new Date());
     }

     public static String FormatAsUtc(Date date)
     {
    	  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
			return f.format(date);
     }
}