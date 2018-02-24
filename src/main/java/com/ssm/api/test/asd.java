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

import org.apache.commons.lang.StringUtils;

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
//	    s9();
//	    s10();
//	   System.out.println( filter("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]"+"tt"));
//	   System.out.println( replaceEmoji(patternString));
//	    ssqwe();
	    System.out.println("[\uD83C\uDC04-\uD83C\uDE1A]");
	  System.out.println( filterEmoji("有表情的字符串"+"[\uD83C\uDC04-\uD83C\uDE1A]","把表情替换成这个字符串"));
	}
	/**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source,String slipStr) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }
	
	public static String replaceEmoji(String str) {
        String patternString = "[\uD83C\uDC04-\uD83C\uDE1A]|[\uD83D\uDC66-\uD83D\uDC69]|[\uD83D\uDC66\uD83C\uDFFB-\uD83D\uDC69\uD83C\uDFFF]|[\uD83D\uDE45\uD83C\uDFFB-\uD83D\uDE4F\uD83C\uDFFF]|[\uD83C\uDC00-\uD83D\uDFFF]|[\uD83E\uDD10-\uD83E\uDDC0]|[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEF6]"+"asdsa";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher("");
        return matcher.replaceAll("");
    }
	
	public static void s10(){
		int i=100;
		for (int j = 0; j < i; j++) {
			System.out.print("fuck"+j);
		}
	}
	
	
	
	public static void s9(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append("asdasd").append(",");
		}
		System.out.println(sb);
		String string = sb.toString();
		System.out.println(string.substring(0,string.length()-1));
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
