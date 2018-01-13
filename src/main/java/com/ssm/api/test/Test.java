package com.ssm.api.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	
	/**
	 * if(true) 不加大括号
	 */
//	@org.junit.Test
	public  void test4(){
		int ss=4;
		int forum_free=0;
		if(true) forum_free = ss;
		System.out.println(forum_free);
	}

	/**
	 * 字符串转时间戳
	 */
//	@org.junit.Test
	public void test5(){
		 String string = "2014-3-17 08:20";
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	            Date parse = null;
				try {
					parse = dateFormat.parse(string);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        System.out.println(parse);
	}
	
	/**
	 * 对象赋值是把该对象的引用指向另一个对象
	 */
//	@org.junit.Test
	public void test(){
		int a[] = {100,200,300};
		int b[] = {11,12,13,14,15,16};
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]+"===");
		}
		b=a;
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
		b[0]=123456;
		System.out.println(a[0]+"---"+a[1]+"---"+a[2]);
		System.out.println(b[0]+"---"+b[1]+"---"+b[2]);
		System.out.println(b.length);
	}
	
//	@org.junit.Test
	public void test1(){
		String a = new String("试试");
		String b = new String("试阿打算多试");
		b=a;
		System.out.println(b);
		b="dasda";
		System.out.println(a+b);
	}
	
	/**
	 * 获取当前时间后一天的时间戳
	 */
//	@org.junit.Test
	public void test2(){
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,60*24);
		int timeInMillis =(int) (now.getTimeInMillis()/1000);
		System.out.println(timeInMillis);
	}
	
	/**
	 * 获取当前时间后一小时的时间（字符串格式）
	 */
//	@org.junit.Test
	public void test3(){
		Calendar now=Calendar.getInstance();
		  now.add(Calendar.MINUTE,60);
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr=sdf.format(now.getTimeInMillis());
		  System.out.println(dateStr);
	}
}
