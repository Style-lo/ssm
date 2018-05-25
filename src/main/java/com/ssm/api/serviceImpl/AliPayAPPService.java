package com.ssm.api.serviceImpl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ssm.api.bean.entity.pay.AlipayAppConfig;
import com.ssm.api.bean.entity.pay.AlipayReturn;
import com.ssm.api.utils.PayUtils;

public class AliPayAPPService {

	static final String privateKey="alipay.privateKey";//支付宝私钥
	
	public AlipayReturn buildAlipayParam() {
		Map<String, String> paramMap = new TreeMap<>();
		paramMap.put("out_trade_no", PayUtils.getPaySnByUUId());
		paramMap.put("subject", "App支付测试Java");
		paramMap.put("body", "我是测试数据");
		paramMap.put("notify_url", "回调地址");
		paramMap.put("total_fee", "0.01");

		// 系统参数
		paramMap.put("partner", "2088121633145095");// 合作身份者id，以2088开头的16位纯数字
		paramMap.put("seller_id", "reg@yecai360.com");// 卖家支付宝帐户,必填
		paramMap.put("payment_type", "1");
		paramMap.put("it_b_pay", "30m");
		paramMap.put("show_url", "m.alipay.com");
		paramMap.put("service", "mobile.securitypay.pay");
		paramMap.put("_input_charset", "utf-8");
		
		try {
			String sign = AlipaySignature.rsaSign(this.mapToString(paramMap), privateKey, "utf-8");
			paramMap.put("sign", URLEncoder.encode(sign, "utf-8"));
			paramMap.put("sign_type", "RSA");
		} catch (Exception e) {
			e.printStackTrace();
		}

		AlipayReturn alipayReturn = new AlipayReturn();
		alipayReturn.setConfig(this.mapToString(paramMap));
		return alipayReturn;
	}
	
	private String mapToString(Map<String, String> map) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			builder.append("&").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
		}
		builder.deleteCharAt(0);

		return builder.toString();
	}
	
	/**
	 * 支付宝退款
	 * @param trade_no 外部交易订单号
	 * @param order_amount 金额
	 * @return
	 */
	public AlipayTradeRefundResponse alipayReturnLo(String trade_no,String order_amount){
		//实例化客户端
		 AlipayClient client = new DefaultAlipayClient(AlipayAppConfig.URL, AlipayAppConfig.APPID, AlipayAppConfig.RSA_PRIVATE_KEY, AlipayAppConfig.FORMAT, AlipayAppConfig.CHARSET, AlipayAppConfig.ALIPAY_PUBLIC_KEY,AlipayAppConfig.SIGNTYPE); 
		 AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		 //把订单号和退款金额已Json格式传给支付宝
	     Map<String, String> data = new HashMap<String, String>();
	     data.put("trade_no", trade_no);
	     data.put("refund_amount", "0.01");
	     request.setBizContent(JSON.toJSONString(data));
	     
	     AlipayTradeRefundResponse response= null;
		try {
			response = client.execute(request);
			return response;
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return response;
		}
		
	}
}
