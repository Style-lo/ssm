package com.ssm.api.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * AES加密工具
 * 
 * @author DerekFung
 */
public class EncrypDES {

	public static final String AES_KEY = "dhH78U+myTnB2O9gvEzlQA==";
	
	public static final String ORDER_KEY = "yE3YV937GKvu3DgmA8RN6w==";
	
	public static final String OTHER_KEY = "Gx0BUC4ohPO3Q9lq2J9qvQ==";
	
	/**
	 * 加密其他内容
	 */
	public static String encodeOther(String content) {
		byte[] key = Base64.decode(EncrypDES.OTHER_KEY);
		AES aes = SecureUtil.aes(key);
		return aes.encryptHex(String.valueOf(content));
	}
	
	/**
	 * 解密其他内容
	 */
	public static String decodeOther(String content) {
		byte[] key = Base64.decode(EncrypDES.ORDER_KEY);
		AES aes = SecureUtil.aes(key);
		return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
	}
	
	/**
	 * 加密订单id
	 */
	public static String encodeOrder(int id) {
		byte[] key = Base64.decode(EncrypDES.ORDER_KEY);
		AES aes = SecureUtil.aes(key);
		return aes.encryptHex(String.valueOf(id));
	}
	
	/**
	 * 解密订单id
	 */
	public static int decodeOrder(String content) {
		byte[] key = Base64.decode(EncrypDES.ORDER_KEY);
		AES aes = SecureUtil.aes(key);
		String id = aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
		return Integer.valueOf(id);
	}
	
	/**
	 * 加密积分
	 */
	public static String encodePoint(String content) {
		byte[] key = Base64.decode(EncrypDES.AES_KEY);
		AES aes = SecureUtil.aes(key);
		return aes.encryptHex(content);
	}

	/**
	 * 解密积分
	 */
	public static String decodePoint(String content) {
		byte[] key = Base64.decode(EncrypDES.AES_KEY);
		AES aes = SecureUtil.aes(key);
		return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
	}

	public static void main(String[] args) {
		String str = "123123qwe";
		System.out.println("加密前："+str);
		String encodePoint = encodePoint(str);
		System.out.println("加密后："+encodePoint);
		System.out.println("解密后："+decodePoint(encodePoint));
	}
}
