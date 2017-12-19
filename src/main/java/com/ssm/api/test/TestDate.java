package com.ssm.api.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
