package com.ssm.api.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapAndHashTabe {

	public void s(){
		ConcurrentHashMap<String , String > map = new ConcurrentHashMap<String, String>();
		Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
	}
	
	public void test(){
		double d = 0;
		System.out.println(++d);
	}
	public void test2(){
		String str1 ="abc";
		String str2 ="";
		
		StringBuffer sdf = new StringBuffer();
		sdf.append(str1);
		
		str2=sdf.toString();
		
		System.out.println(str2);
	}
	public static void main(String[] args) {
		HashMapAndHashTabe tes = new HashMapAndHashTabe();
		tes.test2();
	}
}
