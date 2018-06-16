package com.ssm.api.test;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
		TestJson ts = new TestJson();
//		ts.s4();
//		s5();
		ts.s9();
	}
	
	public  void s9(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ss", "11");
		map.put("ss", "22");
		
		for (int i = 0; i < 26; i++) {
			System.out.print((char)(i+'A'));
		}
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("q"+i);
		}
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}
	}	
	
	public  void s8(){
		long time = System.currentTimeMillis();
	int j;
	boolean flag;
	long b=0;
	long count=0;
	for(int i=2;i<100;i++){
		flag=false;
		for(j=2;j<=Math.sqrt(i);j++){
			if(i%j==0){
				b+=i;
				++count;
				break;
			}
		}
	}
	    System.out.println("质素和="+b);
	    System.out.println("count="+count);
	    long currentTimeMillis = System.currentTimeMillis();
	    System.out.println(currentTimeMillis-time);
	    System.out.println("svg="+b/count);
	}
	
	public  void s7(){
		Map<String, String> map = null;
		int b =0;
		s8(map,b);
		System.out.println(b);
		System.out.println(map);
	}
	public  void s8(Map<String, String> map,int b){//map=null
		b=5;
//		System.out.println("----"+map.hashCode());
		map = new HashMap<String, String>();//一个新map了  map = "地址"
		System.out.println("----"+map.hashCode());
		map.put("1", "111");
		System.out.println("----"+map.hashCode());
	}
	
	public static void s6(){
	Map map = new HashMap();
	Map map1 = new HashMap();
	map.put("qq", "375");
	map.put("qq1", "375");
	map.put("qq2", "375");
	map.put("map", map);
	System.out.println(map.get("map"));
		int i=1;
		int j=1;
		int s = 1;
		while (s<100) {
			j++;
			s++;
			if(1==i){
				System.out.print(i+"+"+j);
				i--;
			}else {
				System.out.print("+"+j);
			}
		}
	}	
	
	
	public static void s5(){
		int a=0, c=0;
		do {
			c--;
			a=a-1;
		} while (a>0);
		System.out.println("C:"+c);
		int i=0;
		i=i++;
		System.out.println("i:"+i);
		
		System.out.println( new Timestamp(System.currentTimeMillis()));
		System.out.println(StringUtils.isNotEmpty(""));
		System.out.println(StringUtils.isNotEmpty(" "));
		System.out.println(StringUtils.isNotEmpty("2"));
		System.out.println(StringUtils.isNotEmpty(null));
		
	}
	
	int ssString;
	public void s4(){
		String ssString;
		String q;
		Double q1;
		System.out.println(this.ssString);
		int [] s=new int[5];
		String [] s1=new String[5];
		System.out.println(s[4]);
		System.out.println(s1[4]);
		/*for(int i=0;i<10;i++){
			for(int a=0;a<10;a++){
				System.out.println(a+"a");
			}
			System.out.println(i+"i");
			break ;
		}*/
	}
	public static void s3(){
		DecimalFormat df = new DecimalFormat("#.00");
		double usePointPrice=0.055;
		System.out.println(df.format(usePointPrice));
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
