package com.ssm.api.test;

import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;

public class Md5Test {

	public static void main(String[] args) {
		s1();
	}
	public static void s1() {
		String s ="MyPassword";
		String md5DigestAsHex = "";
		try {
			md5DigestAsHex = DigestUtils.md5DigestAsHex(s.getBytes("UTF-8"));
			System.out.println(md5DigestAsHex);
			String cs ="MyPassword";
			String md5DigestAsHex2 = DigestUtils.md5DigestAsHex(cs.getBytes("UTF-8"));
			if (md5DigestAsHex2.equals(md5DigestAsHex)) {
				System.out.println("登录成功");
			}else {
				System.out.println("登录失败");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
