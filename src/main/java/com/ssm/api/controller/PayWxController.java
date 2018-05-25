package com.ssm.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.api.bean.entity.exception.MyException;
import com.ssm.api.serviceImpl.WXPayService;

@Controller
@RequestMapping("/api/v1")
public class PayWxController {

	@Autowired
	WXPayService wXPayService;
	@RequestMapping("/pay/return")
	public String payCall(HttpServletRequest req, HttpServletResponse response){
		System.out.println("进入回调接口");
		String payCall="FALL";
		try {
			payCall = wXPayService.payCall(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnXML(payCall);
	}
	
	
	@RequestMapping("/return/{pay_sn}")
	public String wxReturn(@PathVariable String pay_sn){
		Map<String, String> wXReturn = wXPayService.wXReturn(pay_sn, 1);
		if(!"SUCCESS".equals(wXReturn.get("return_code").toString())) throw new MyException(wXReturn.get("return_msg").toString());
		if(!"SUCCESS".equals(wXReturn.get("result_code").toString())) throw new MyException(wXReturn.get("err_code_des").toString());
		return "退款成功";
	}
	
	
	
    private String returnXML(String return_code) {

        return "<xml><return_code><![CDATA["+ return_code + "]]></return_code>"
        	+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
