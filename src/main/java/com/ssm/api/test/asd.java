package com.ssm.api.test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class asd {

	
	private static int setSurplusTime(long add_time,int since_hand){
		long current = System.currentTimeMillis()/1000 ;
		int surplusTime = 0 ;
		long time = 0 ;
		if(since_hand==1){
			time = (add_time+30*60)-current ;
		}else{
			time = (add_time+15*60)-current ;
		}
		if(time<0) return 0 ;
		surplusTime = (int) (time/60) ;
		return surplusTime ;
	}
	public static void ss(int ss){
		long time ;
		if (ss==0) {
			time = 1511341454+ 15*60;
			System.out.println(time);
		}else {
			time = 1511341454+ 30*60;
			System.out.println(time);
		}
	}
	
	public static boolean isNow(long date){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(1511436472*1000l);
		Date time=null;
		try {
			time = format.parse(d);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	        //当前时间
	        Date now = new Date();
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	        //获取今天的日期
	        String nowDay = sf.format(now);
	        //对比的时间
	        String day = sf.format(time);
	        System.out.println("time:"+time);
	        System.out.println("day:"+day);
	        System.out.println(Integer.valueOf(day)-Integer.valueOf(nowDay));
	        return day.equals(nowDay);
	}
	
	public static void s2(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		String format = sdf.format(new Date());
		System.out.println(format);
	}
	
	public static void s3(){
		DecimalFormat df = new DecimalFormat("#.00");
		String format = df.format(2.52551);
		System.out.println(format);
	}
	public static void s4(){
		String s = "10046112131";
		System.out.println(Integer.valueOf(s));
	}
	
	public static void main(String[] args) {
		s4();
//		s3();
//		s2();
		System.out.println(setSurplusTime(1511318393,1));
		boolean result = isNow(1508671672);
		if (result) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
        System.out.println( result?"是今天。":"不是今天。" );
		ss(1);
	}
}
