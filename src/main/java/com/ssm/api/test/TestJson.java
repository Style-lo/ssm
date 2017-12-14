package com.ssm.api.test;

import java.util.Random;

import net.sf.json.JSONObject;

import com.ssm.api.bean.request.User;

public class TestJson {

	public static void main(String[] args) {
		/*User user = new User();
		user.setId(3);
		user.setUser_name("sdsd");
		user.setAge(22);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ids", user.getId());
		jsonObject.put("ages", user.getAge());
		jsonObject.put("names", user.getUser_name());
		System.out.println(jsonObject);*/
		/*for (int i = 0; i < 20; i++) {
			Random rand = new Random();
			int nextInt = rand.nextInt(99);
			System.out.println(nextInt+"Row");
			double d = Math.random();
			int ss = (int)(Math.random()*50+51);
			String chars = "abcdefghijklmnopqrstuvwxyz";
			char charAt = chars.charAt((int)(Math.random() * 26));
			char charAt2 = chars.charAt((int)(Math.random() * 26));
		}*/
//		s1();
		s2();
	}
	public static void s3(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ss", true);
		if (jsonObject.getBoolean("ss")) {
			
		}
	}
	public static void s2(){
		String ss = "789622.0";
		int s1 =2;
		System.out.println(Integer.valueOf(s1));
		System.out.println(Integer.valueOf(ss));
	}
	public static void s1(){
		int [] s = {3,5,1,9,6,4,7};
		for (int i = 0; i < s.length-1; i++) {
			for (int j = 0; j < s.length-1-i; j++) {
				if(s[j]>s[j+1]){
			int temp=s[j];
			s[j]=s[j+1];
			s[j+1]=temp;
			}
			}
		}
		for (int i = 0; i < s.length; i++) {
			
			System.out.println(s[i]);
		}
	}
}
