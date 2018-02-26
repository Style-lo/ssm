package com.ssm.api.service;

import javax.servlet.http.HttpServletResponse;

import com.ssm.api.bean.request.AliPayRequest;

public interface AliPCPayService {

	String pcPay(AliPayRequest aliPayRequest);
}
