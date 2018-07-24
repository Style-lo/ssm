package com.ssm.api.utils;

import java.util.Date;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

public class DateUtils {

	/**
	 * 获取某段时候后的时间
	 */
	public static void ss(){
		DateTime offset = DateUtil.offset(new Date(), DateField.HOUR_OF_DAY , 24*2);
		System.out.println(offset);
	}
	/**
	 * 字符串转时间
	 */
	public static void strToDate(){
		String time = "2018-06-02";
		DateTime parseDate = DateUtil.parseDate(time);
		System.out.println(parseDate);
	}
	public static void s1(){
		DateTime offset = DateUtil.offset(new Date(), DateField.HOUR_OF_DAY , 24*2);
		long betweenDay = DateUtil.between(new Date(),offset,DateUnit.MINUTE,false);
		System.out.println(betweenDay);
		long t = betweenDay/1440;
		System.out.println(t);//获取天数
		long h= betweenDay%24;
		System.out.println(h);//获取小时
		long m =betweenDay%60;
		System.out.println(m);//获取分钟
		System.out.println(t+"天"+h+"小时"+m+"分钟");
	}
	public static void main(String[] args) {
		s1();
	}
}
