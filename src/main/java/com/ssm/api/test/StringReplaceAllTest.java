package com.ssm.api.test;


public class StringReplaceAllTest {

	public static void main(String[] args) {
		s1();
	}
	/**
	 * 字符串替换
	 */
	public static void s1(){
		String cardNo ="621   333 5445 55";
		String ss = cardNo.replaceAll("\\s*", "");
		String ss2 = cardNo.replaceAll("\\s", "-");
		System.out.println("替换前"+cardNo);
		System.out.println("替换后"+ss);
		System.out.println("替换后"+ss2);
	}
	
	public static void s2(){
		String s="shop_outtime";
		System.out.println(s.indexOf("shop"));
		System.out.println(s.indexOf("shop2"));
	}
}
