package com.ssm.api.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {

	@Test
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
	@Test
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
	@Test
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
	public static void main(String[] args) {
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
	}
}
