package com.ssm.api.bean.request;

public class AliPayRequest {

	//商户订单号，商户网站订单系统中唯一订单号，必填
	private String out_trade_no;
	//付款金额，必填
	private String total_amount;
	//订单名称，必填
	private String subject;
	//商品描述，可空
	private String body;
	
	public AliPayRequest(String out_trade_no, String total_amount,
			String subject, String body) {
		super();
		this.out_trade_no = out_trade_no;
		this.total_amount = total_amount;
		this.subject = subject;
		this.body = body;
	}
	public AliPayRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
