package com.ssm.api.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
	public static SimpleDateFormat date2Format = new SimpleDateFormat("yyyy-MM-dd");
//	@Test
	public void s1(){
		int s =1513576938;
		if (s < new Date().getTime()/1000) {
			System.out.println("正确");
		}else {
			System.out.println("错误");
		}
	}
	
	/**
	 * 判断一个时间是否是当天
	 */
//	@Test
	public void s2(){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int s =1513576938;
		String start = format.format(s*1000l);  
	    try {
			Date startTime =format.parse(start);
			Date now = new Date();
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	        //获取今天的日期
	        String nowDay = sf.format(now);
	        //对比的时间
	        String day = sf.format(startTime);
	        if (day.equals(nowDay)) {
	        	System.out.println("是当天时间");
			}else {
				System.out.println("不是当天时间");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取指定时间的时间戳
	 * @param hh	时
	 * @param mm	分
	 * @param ss	秒
	 * @return	时间戳
	 */
	public static long getCurrentTime(int hh,int mm, int ss) {
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
				calendar2.get(Calendar.DAY_OF_MONTH), hh, mm, ss);
		Date endOfDate = calendar2.getTime();
		return endOfDate.getTime() / 1000;
	
	}
	/*public static void main(String[] args) {
		long currentTime = getCurrentTime(9,0,0);
		long currentTime2 = getCurrentTime(11,59,59);
		System.out.println(currentTime);
		System.out.println("11:"+currentTime2);
	}*/
//	@Test
	public void s3(){
		String date = "2017-11-20 20:30";
		SimpleDateFormat sldf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date parse = sldf.parse(date);
			long ss = parse.getTime()/1000;
			System.out.println(ss);
			System.out.println(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void s4(Date date,int hh,int mm,int ss){
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.set(day.get(Calendar.YEAR), day.get(Calendar.MONTH),
				day.get(Calendar.DAY_OF_MONTH), hh, mm, ss);
		Date time = day.getTime();
		long time2 = time.getTime()/1000;
		System.out.println(time);
		System.out.println(time2);
	}
	/*public static void main(String[] args) {
		String ss = "2017-11-05 23:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date parse=null;
		try {
			parse = sdf.parse(ss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s4(parse, 21 ,0,0);
//		night();
		//获取当前时间，到毫秒
//		System.out.println(new Timestamp(System.currentTimeMillis()));
		String beforDayTime = getBeforDayTime(new Date(),-3);
		System.out.println(beforDayTime);
	}*/

	    /**
	     * 夜间服务费
	     * 21:00-00:00 2元
	     * 00:00-02:00 3元
	     * 02:00-06:00 5元
	     */
	    public static int night(){
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
	    	String format = dateFormat.format(new Date());
	    	System.out.println(format);
	        int value = Integer.valueOf(dateFormat.format(new Date()));
	        System.out.println(value);
	        if(value == 0 || value >= 2100){
	            return 2;
	        } else if(value <= 200){
	            return 3;
	        } else if(value <= 600){
	            return 5;
	        }

	        return 0;
	    }
	    /**
	     * 获取指定时间的前后时间
	     * @param date	指定时间
	     * @param day	指定时间前多少天（-1为昨天）
	     * @return
	     */
	    public static String getBeforDayTime(Date date, int day) {
	    	SimpleDateFormat date2Format = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.DATE, day);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        return date2Format.format(calendar.getTime());
	    }
	    
	    
	    /**
	     * 获取当月开始时间
	     * @return
	     */
	    public static String getMonthFirstDay(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        if(date != null) {
	        	calendar.setTime(date);
	        }
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	        return date2Format.format(calendar.getTime());
	    }
	    /**
	     * 获取当月开始时间
	     * @return
	     */
	    public static String getMonthLastDay(Date date) {
	    	Calendar calendar = Calendar.getInstance();
	    	if(date != null) {
	    		calendar.setTime(date);
	    	}
	    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    	return date2Format.format(calendar.getTime());
	    }
	    
	    public static void s7(){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			System.out.println(calendar.get(Calendar.MONTH)+"----888888----");
			System.out.println((calendar.get(Calendar.MONTH) + 1));
			System.out.println(calendar.get(Calendar.YEAR)+"===========");
			System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1));
			System.out.println(getMonthFirstDay(calendar.getTime())+"*********************");
			System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	    public static void main(String[] args) {
	    	s7();
		}
}
