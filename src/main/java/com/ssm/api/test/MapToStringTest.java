package com.ssm.api.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapToStringTest {
	/** * * ━━━━━━神兽出没━━━━━━ 
	* 　　　┏┓　　　┏┓ 
	* 　　┃　　　　　　　┃ 
	* 　　┃　　　━　　　┃ 
	* 　　┃　┳┛　┗┳　┃ 
	* 　　┃　　　　　　　┃ 
	* 　　┃　　　┻　　　┃ 
	* 　　┃　　　　　　　┃ 
	* 　　┗━┓　　　┏━┛Code is far away from bug with the animal rotecting 
	* 　　　　┃　　　┃ 神兽保佑,代码无bug 
	* 　　　　┃　　　┃ 
	* 　　　　┃　　　┗━━━┓ 
	* 　　　　┃　　　　　　　┣┓ 
	* 　　　　┃　　　　　　　┏┛ 
	* 　　　　┗┓┓┏━┳┓┏┛ 
	* 　　　　　┃┫┫　┃┫┫ 
	* 　　　　　┗┻┛　┗┻┛ 
	* * ━━━━━━感觉萌萌哒━━━━━━ */ 
	
	
	/**
	 *　　　　　　　 ┏┓       ┏┓+ +
	 *　　　　　　　┏┛┻━━━━━━━┛┻┓ + +
	 *　　　　　　　┃　　　　　　 ┃
	 *　　　　　　　┃　　　━　　　┃ ++ + + +
	 *　　　　　　 ████━████  ┃+
	 *　　　　　　　┃　　　　　　 ┃ +
	 *　　　　　　　┃　　　┻　　　┃
	 *　　　　　　　┃　　　　　　 ┃ + +
	 *　　　　　　　┗━━┓　　　 ┏━┛
	 *               ┃　　  ┃
	 *　　　　　　　　　┃　　  ┃ + + + +
	 *　　　　　　　　　┃　　　┃　Code is far away from     bug with the animal protecting
	 *　　　　　　　　　┃　　　┃ + 　　　　         神兽保佑,代码无bug
	 *　　　　　　　　　┃　　　┃
	 *　　　　　　　　　┃　　　┃　　+
	 *　　　　　　　　　┃　 　 ┗━━━┓ + +
	 *　　　　　　　　　┃ 　　　　　┣┓
	 *　　　　　　　　　┃ 　　　　　┏┛
	 *　　　　　　　　　┗┓┓┏━━━┳┓┏┛ + + + +
	 *　　　　　　　　　 ┃┫┫　 ┃┫┫
	 *　　　　　　　　　 ┗┻┛　 ┗┻┛+ + + +
	 */
	
//  ┏┛ ┻━━━━━┛ ┻┓
//  ┃　　　　　　 ┃
//  ┃　　　━　　　┃
//  ┃　┳┛　  ┗┳　┃
//  ┃　　　　　　 ┃
//  ┃　　　┻　　　┃
//  ┃　　　　　　 ┃
//  ┗━┓　　　┏━━━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━━━━━━━┓
//    ┃　　　　　　　    ┣┓
//    ┃　　　　         ┏┛
//    ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
//      ┃ ┫ ┫   ┃ ┫ ┫
//      ┗━┻━┛   ┗━┻━┛
	public static void s(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "123");
		map.put("name", "张三");
		map.put("age", "20");
		map.put("like", "敲代码");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append("&").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
		}
		String string = String.valueOf(sb);
		System.out.println(string.substring(1));
		sb.deleteCharAt(0);
		System.out.println(sb);
	}
	public static void main(String[] args) {
		s();
	}
}
