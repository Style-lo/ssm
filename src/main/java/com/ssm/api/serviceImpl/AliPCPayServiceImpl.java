package com.ssm.api.serviceImpl;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ssm.api.bean.entity.AlipayConfig;
import com.ssm.api.bean.request.AliPayRequest;
import com.ssm.api.service.AliPCPayService;

@Service("aliPCPayServiceImpl")
public class AliPCPayServiceImpl implements AliPCPayService{

	@Override
	public String pcPay(AliPayRequest aliPayRequest) {
		// TODO Auto-generated method stub


	      //获得初始化的AlipayClient
	    	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	        //设置请求参数
	    	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        alipayRequest.setReturnUrl("http://localhost:8090/ssm/return_url");
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

	        alipayRequest.setBizContent("{\"out_trade_no\":\""+ aliPayRequest.getOut_trade_no() +"\"," 
	                + "\"total_amount\":\""+ aliPayRequest.getTotal_amount() +"\"," 
	                + "\"subject\":\""+ aliPayRequest.getSubject() +"\"," 
	                + "\"body\":\""+ aliPayRequest.getBody() +"\"," 
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        System.out.println(alipayRequest.getBizContent().toString());
	        //请求
	        String result = null;
			try {
				result = alipayClient.pageExecute(alipayRequest).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	}

}
