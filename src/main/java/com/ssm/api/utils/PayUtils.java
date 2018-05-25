package com.ssm.api.utils;

import java.util.Date;
import java.util.UUID;


public class PayUtils {

	/**
	 * 支付单号
	 * 
	 * @return
	 */
	public static String getPaySnByUUId() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		long time = new Date().getTime();
		String s12 = "" + time + "" + hashCodeV;
		if (s12.length() <= 20) {
			s12 = s12 + "00000";
			s12 = s12.substring(0, 20);
		}
		return s12;

	}
}
