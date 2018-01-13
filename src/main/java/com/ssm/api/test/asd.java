package com.ssm.api.test;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asd {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
	
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
	public static void s6(){
		String ss = "0000";
		try {
			Date parse = dateFormat.parse(ss);
			System.out.println(Integer.valueOf(dateFormat.format(parse)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 正则表达式验证手机号
	 * @param mobile
	 * @return
	 */
	private static boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
	
	public static void s7(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		System.out.println(calendar.get(Calendar.MONTH)+"-------------");
		System.out.println((calendar.get(Calendar.MONTH) + 1));
		System.out.println(calendar.getTimeInMillis());
		System.out.println(calendar.get(Calendar.YEAR)+"-------------");
	}
	
	/**
	 * id生成器
	 */
		public static String buildId(){
	        String uuid = UUID.randomUUID().toString();
	        System.out.println(uuid);
	        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	    }
	
		public static void s8(){
			System.out.println(new Timestamp(System.currentTimeMillis()));
		}
		
	public static void main(String[] args) {
//		s8();
		Integer a = 1000, b = 1000;  
	    System.out.println(a == b);//1
	    Integer c = -128, d = -128;  
	    System.out.println(c == d);//2

//		System.out.println(buildId());
//		s7();
//		System.out.println((int)((Math.random() * 9 + 1) * 100000));
//		System.out.println(checkMobile("121000000000"));
//		s6();
//		s5();
//		s4();
//		s3();
//		s2();
		/*System.out.println(setSurplusTime(1511318393,1));
		boolean result = isNow(1508671672);
		if (result) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
        System.out.println( result?"是今天。":"不是今天。" );
		ss(1);*/
	}
	
	public static void s5(){
		String ss = null;
		if ("".equals(ss)) {
			System.out.println("5555");
		}else {
			System.out.println("=++++");
		}
		if (ss.equals("ss")) {
			System.out.println("-----");
		}
	}
	
}
