package com.edge.dateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
public class SqlDateTime {

	  public static void main(String[] args) {
		 System.out.println(LocalDateTime.now());
	    java.util.Date javaDate = new java.util.Date();
	    long javaTime = javaDate.getTime();
	    System.out.println("The Java Date is: " + javaDate.toString());

	    java.sql.Date sqlDate = new java.sql.Date(javaTime);
	    System.out.println("The SQL DATE is: " + sqlDate.toString());

	    java.sql.Time sqlTime = new java.sql.Time(javaTime);
	    System.out.println("The SQL TIME is: " + sqlTime.toString());

	    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
	    System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
	    
	    java.util.Date today = new java.util.Date();
	    System.out.println(new java.sql.Timestamp(today.getTime()));
	    
	    Date now=DateUtils.truncate(new java.sql.Timestamp(today.getTime()), Calendar.SECOND);
	    System.out.println(DateUtils.truncate(new java.sql.Timestamp(today.getTime()), Calendar.SECOND));
	    
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	  }
	}
