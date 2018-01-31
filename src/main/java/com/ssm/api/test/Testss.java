package com.ssm.api.test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;

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
//		testss.s1();
//		testss.s2();
//		md5();
//		s3();
//		s4();
//		s5(2200l, 4.2);
//		s6(0.4645);
//		s7();
//		s8();
//		s10();
//		s12();
//		s13();
		s14();
	}
	
	public static void s14(){
		int s =2;
		int d = 1;
		s=++d;
		System.out.println(s);
		System.out.println(d);
	}
	public static void s13(){
		JSONObject jsonObjString = new JSONObject();
		jsonObjString.put("id", 12);
		jsonObjString.put("name", "yusdesad");
		jsonObjString.put("ahe", "dsfsdf");
		jsonObjString.put("sss", 45);
		System.out.println(jsonObjString.toString());
	}
	public static void s12(){
		Dog dog = new Dog();
		dog.setName("");
		System.out.println(dog.getName());
		int s=12;
		String sssString=s+dog.getName();
		System.out.println(sssString);
	}
	public static void s11(){
		Map<String, List> s92 = s92();
		List list = s92.get("list");
		for (Object object : list) {
			System.out.println(object);
		}
	}
	public static void s10(){
		Map<String ,Double> s91 = s91();
		Double double1 = s91.get("id");
		System.out.println("double: "+double1);
		Map s9 = s9();
		System.out.println(s9.get("name")+"----------");
		if (s9.get("name") != null && !"".equals(s9.get("name"))) {
			Object object = s9.get("id");
			System.out.println(object);
			System.out.println("判断");
		}else {
			System.out.println(false);
		}
	}
	public static Map s9(){
		Map map = new HashMap();
		map.put("id", 12);
		map.put("name", "dasdsad");
		map.put("name", "3243224");
		return map;
	}
	public static Map<String ,Double> s91(){
		Map<String ,Double> map = new HashMap();
//		map.put("id", 12.0);
		return map;
	}
	public static Map<String ,List> s92(){
		Map<String ,List> map = new HashMap();
		List list = new ArrayList();
		list.add("￥"+2);
		list.add(2);
		list.add("满送");
		list.add("http://www.baidu.com/");
		map.put("list", list);
		return map;
	}
	
	public static void s8(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(2);
		list.add(3);
		System.out.println(list.get(0));
	}
	
	public static void s7(){
		double s=5.2;
		s8(s);
		System.out.println(s);
		
	}
	public static void s8(double s){
		 s=5.5;
		
	}
	public static void s6(double d){
		double s =(double)Math.round(d*100)/100;
		System.out.println(s);
	}
	
	@SuppressWarnings("unused")
	public static void s5(Long guideDistances,double mon){
		double pack=0.0;
		List<Double> lists = new ArrayList<Double>();
		lists.add(0.5);
		lists.add(1.0);
		lists.add(1.5);
		lists.add(2.0);
		if (true) {
			for (Double list : lists) {
				Long distance2 = (long) ((list*2) *1000);
				if (guideDistances <= distance2) {
					pack = s6(distance2);
					if (mon < pack ) {//运费大于包邮
						pack = mon;
						double s = pack/2;
						pack = s;
					}
					break;
				}else {
					pack = s6(distance2);
					if (mon > pack ) {//运费小于包邮
						double s = pack/2;
						pack = s ;
					}
				}
			}
		}else {
			Double double1 = lists.get(lists.size()-1);
			System.out.println(double1);
				Long distance2 = (long) (double1 *1000);
				if (mon < distance2) {
					pack = mon;
				}else {
					pack = distance2;
				}
		}
		System.out.println(pack);
	}
	public static double s6(Long s){
		if (s==1000) {
			return 3.8;
		}else if (s==2000) {
			return 5.8;
		}else if (s==3000) {
			return 7.8;
		}else if (s==4000) {
			return 9.8;
		}else if (s==5000) {
			return 11.8;
		}
		return 0.0;
	}
	
	
	
	public static void s4(){
		double s = 1.99;
		double ceil = Math.ceil(s);
		int w = (int) s;
		System.out.println(ceil);
	}
	public static void s3(){
		int s = 1;
		int s1 ;
		s1=s;
		int s2=2;
		s=s2;
		System.out.println(s+"====="+s1+"======"+s2);
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
