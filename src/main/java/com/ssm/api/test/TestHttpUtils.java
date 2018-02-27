package com.ssm.api.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.api.utils.HttpUtils;
import com.ssm.api.utils.HttpUtils.Result;

public class TestHttpUtils {

	public static JSONObject getCharge(String areaCode){
		String url = "http://112.74.28.99:8081"+"/api/v1/serviceCharge/"+areaCode ;
		Map map = new HashMap<String,Object>() ;
		map.put("api", "web") ;
		Result result = HttpUtils.get(url, map) ;
		JSONObject jsonObject = result.toJSONObject();
		return jsonObject ;
	}
	public static void main(String[] args) {
		JSONObject charge = getCharge("440114");
		 JSONArray jsonArray = charge.getJSONObject("data").getJSONArray("carriageInfo");
		 for (int i = 0; i < jsonArray.size(); i++) {
			 JSONObject jsonObject = jsonArray.getJSONObject(i);
			 String key = jsonObject.getString("key");
			 String value = jsonObject.getString("value");
			 System.out.println(key+"======"+value);
		 }
		 String string = charge.getJSONObject("data").get("festivalCharge").toString();
		System.out.println(string);
		System.out.println(charge.toJSONString());
	}
}
