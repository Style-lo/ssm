package com.ssm.api.test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.util.DigestUtils;

public class Testss {

	int s;
	public void s1(){
		int t = (int)(Math.random()*20);
		s=t;
		System.out.println(s+"sssssssss");
	}
	public void s2(){
		int q=s;
		System.out.println(q+"qqqqqqqqq");
	}
	
	public static void md5(){
		String ssString = "sdsf21sdfsd";
		try {
			String dd = DigestUtils.md5DigestAsHex(ssString.getBytes("utf-8"));
			System.out.println(dd);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Testss testss = new Testss();
		testss.s1();
		testss.s2();
		md5();
	}
	
	public static String getOrderIdByUUId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String format = sdf.format(new Date());
		int machineId = (Integer.valueOf(format) % 9)+1;
//		int machineId = 8;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}
}
