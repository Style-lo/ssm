package com.ssm.api.test;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(getPriceStr("50.289"));
	}
	public static String getPriceStr(String priceStr) {
		return NumberFormat.getInstance()
				.format(new BigDecimal(priceStr).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()).toString()
				.replace(",", "");
	}
}
