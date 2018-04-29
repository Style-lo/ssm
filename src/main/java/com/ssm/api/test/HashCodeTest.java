package com.ssm.api.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HashCodeTest {

	public static void main(String[] args) {
		List<String> list =null;
		if (1==2) {
			list = new ArrayList<String>();
			list.add("ss");
		}
		for (String object : list) {
			System.out.println(object);
		}
		System.out.println("----------------------");
		DecimalFormat df=new DecimalFormat(".##");  
		double d=1252;  
		String st=df.format(d);  
		System.out.println(st);  
		
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
	System.out.println("------------------------------------------------");
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;

        System.out.println(f1 == f2);//true
        System.out.println(f3 == f4);//false
        System.out.println(f3.equals(f4));
        
//        主方法是不能调用非静态内部类的 
        Test test2 = new Test();
	}

	


//去掉static 上面会报错
	static class Test{
		private String name;
		
		public Test() {
			super();
			System.out.println("ssss");
		}
		
	}
}
