package com.ssm.api.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Test1 {

	 public static void main(String[] args) throws ParseException {/*  
		 long t2 = 1510899600l*1000;
		 long t1 = 1510900500l*1000;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = new Date(t2);
//	        String format = simpleDateFormat.format(date);
	        long date2 = new Date().getTime()/1000;
//	        String format2 = simpleDateFormat.format(date2);
	        System.out.println(date);
	        System.out.println(date2);
	        long a = date2-t2;
	        String format2 = simpleDateFormat.format(a);
	        System.out.println(format2);
	    */
//		 s6();
		 
//		 ss1();
//		 s5();
//		 s4();
//		 s3();
//		 s6();
//		 ss4();
//		 s7();
		/* Date date = new Date();
		 date.setTime(date.getTime() + 60*60*1000l);
		 s8(new Date(),date );
		 System.out.println(date);*/
		 s9();
	 }  
	 //@Test
	 public static void ss4(){
		 Date now = new Date();
			now.setTime(new Date().getTime()+ 30*60*1000);
				Date date = new Date();
				System.out.println(now);
	 }
	 //@Test
	 public void ss() throws ParseException{
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 java.util.Date now = df.parse("2004-03-26 13:31:40");
		 java.util.Date date=df.parse("2004-01-02 11:30:24");
		 long l=now.getTime()-date.getTime();
		 long day=l/(24*60*60*1000);
		 long hour=(l/(60*60*1000)-day*24);
		 long min=((l/(60*1000))-day*24*60-hour*60);
		 long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		 System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
	 }
	 
	 public void s1(){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try
		 {
		 Date d1 = df.parse("2004-03-26 13:31:40");
		 Date d2 = df.parse("2004-01-02 11:30:24");
		 long diff = d1.getTime() - d2.getTime();
		 long days = diff / (1000 * 60 * 60 * 24);
		 }
		 catch (Exception e)
		 {
		 }
	 }
	 

	 	//@Test
		 public  void s3() throws ParseException{
		     Date date = new Date();
		     long t2 = 1510904400l*1000;
				date.setTime(t2 + 30*60*1000);
				String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(date);
				System.out.println(format);
				String format2 = "2017-12-12 02:22:56";
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date d=sdf.parse(format2);
			    System.out.println("d"+d);
			    System.out.println(d.getTime()/1000); 
			   long ss = d.getTime();
			  long sw = t2-new Date().getTime();
			  System.out.println(sw);
		      }
	 
	 	public static void s4() throws ParseException{
	 		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	 		String fromDate = simpleFormat.format("2016-05-01 12:00");
	 		System.out.println(fromDate);
	 		String toDate = simpleFormat.format("2016-05-01 12:50");  
	 		long from = simpleFormat.parse(fromDate).getTime();  
	 		long to = simpleFormat.parse(toDate).getTime();  
	 		int minutes = (int) ((to - from)/(1000 * 60));  
	 		System.out.println(minutes);
	 	}
	 	
	 	public static void s5(){
	 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	 		  
	 		try  
	 		  
	 		{  
	 			Date d1 = df.parse("2017-11-17 15:40:00");  
	 		  
	 		  
	 		    Date d2 = df.parse("2017-11-17 15:45:00");  
	 		  
	 		    long diff = d1.getTime() - d2.getTime();  
	 		  
	 		    long days = diff / (1000 * 60 * 60 * 24);  
	 		  System.out.println(days);
	 		}  
	 		  
	 		catch (Exception e)  
	 		  
	 		{  
	 		  
	 		}  
	 	}
	 	
	 	
	 	public static void s6() throws ParseException{
	 		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	 		  String s2w = "2017-12-12 02:20:26";
	 		s2w = s2w.substring(0, s2w.length() - 3);
	 	     System.out.println(s2w);
	 	    String format21 = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(s2w);
	 	    System.out.println(format21);
	 	 /* Date now = new Date(1510901400000l);
	 	  Date date = new Date(1510900800000l);*/
	 	   long s1 =1510901400000l;
	 	   long s2 =1510900800000l;
	 	  String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(s1);
	 	  String format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(s2);
	 	   java.util.Date date=df.parse(format);  
	 	  java.util.Date now = df.parse(format2);
	 	  
	 	   long l=now.getTime()-date.getTime();  
	 	  
	 	   long day=l/(24*60*60*1000);  
	 	  
	 	   long hour=(l/(60*60*1000)-day*24);  
	 	  
	 	   long min=((l/(60*1000))-day*24*60-hour*60);  
	 	  
	 	   long s=(l/1000-day*24*60*60-hour*60*60-min*60);  
	 	  
	 	   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");  
	 	}
	 	
	 	public static void ss1(){
	 		Date now = new Date();
			now.setTime(1510900800000l + 30*60*1000);
				Date date = new Date();
				System.out.println(now);
	 	}
	 	public static String getBeforeByHourTime(int ihour){ 
	 	     String returnstr = ""; 
	 	     Calendar calendar = Calendar.getInstance(); 
	 	     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour); 
	 	     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
	 	     returnstr = df.format(calendar.getTime()); 
	 	     return returnstr; 
	 	  } 
	 	public static void s7(){
	 		Date dates = new Date();
	 		dates.setTime(dates.getTime()-70*60*1000);
	 		System.out.println(dates);
	 		
	 		String currentDate = "2017-12-04 14:08:20";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = df.parse(currentDate);// 解析成日期格式
				System.out.println("+++++"+df.format(date));
				date.setTime(date.getTime() - 4 * 60 * 1000);// 减去4分钟以后的时间
				System.out.println(df.format(date));// 再将该时间转换成字符串格式
			} catch (ParseException e) {
				e.printStackTrace();
			}
	 	}
	 	/**
	 	 * 判断两个时间的大小
	 	 * @param millionSeconds
	 	 * @return
	 	 */
	 	public static int s8(Date d1,Date d2){
            if (d1.getTime() > d2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (d1.getTime() < d2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {//相等
                return 0;
            }
	 	}
	 	
	 	public static void s9(){
	 		String ss="2017-12-04 20:30:00";
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		Date format=null;
			try {
				format = sdf.parse(ss);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		System.out.println(format);
	 		String formats = new SimpleDateFormat("HH:mm ").format(format);
	 		System.out.println(formats);
	 	}
}
