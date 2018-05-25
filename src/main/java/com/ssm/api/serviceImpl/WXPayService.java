package com.ssm.api.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.ssm.api.bean.entity.exception.MyException;
import com.ssm.api.bean.entity.pay.WXConfig;

@Service
public class WXPayService {
	private String shopUrl="http://112.74.28.99:8080";
	/*@Autowired
	private ShopncOrdersService orderService;
	@Autowired
	private PayService payService;*/
	@Autowired
	WXConfig config;

	public Object ss1(String order_id,HttpServletRequest request){
		String user_ip = getIp(request);
		
		Map<String, String> unifiedOrder = this.unifiedOrder(order_id, user_ip);
		this.ss(unifiedOrder);
		Map<String, String> buildCallParam = this.buildCallParam(unifiedOrder);
		System.out.println(buildCallParam.toString());
//		ResponseData<Map<String, String>> succeed = ResponseData.succeed(buildCallParam);
		return buildCallParam;
	}
	
	/**
	 * 统一下单，生成预支付单号
	 * @param order_id
	 * @param user_ip
	 * @return
	 */
	public Map<String, String> unifiedOrder (String order_id,String user_ip){
		Map<String, String> map = new HashMap<String, String>();
		map.put("body", "微信测试支付");
		map.put("attach", order_id);
		map.put("out_trade_no", new Date().getTime()+"1214");
		map.put("total_fee", "1");
		map.put("spbill_create_ip", user_ip);
		map.put("notify_url", shopUrl + "/api/v1/pay/return");
		map.put("trade_type", "APP");
		
		Map<String, String> unifiedOrder=null;
		try {
			WXPay wxpay = new WXPay(config);
			unifiedOrder = wxpay.unifiedOrder(map);
			System.out.println("统一请求支付："+unifiedOrder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unifiedOrder;
	}
	
	/**
	 * 构建调起支付，传入预支付中微信返回的订单id(prepay_id)
	 * @param data
	 * @return
	 */
	public Map<String, String> buildCallParam(Map<String, String> data){
		
		Map<String, String> map = new HashMap<String, String>();
		String noncestr = WXPayUtil.generateNonceStr();
        String timestamp = System.currentTimeMillis() / 1000 + "";
        
		map.put("noncestr", noncestr);
		map.put("timestamp", timestamp);
		map.put("package", "Sign=WXPay");
		map.put("prepayid", data.get("prepay_id"));
		map.put("partnerid", config.subMchid);
		map.put("appid", config.subAppId);
		
		String sign="";
		try {
			sign = WXPayUtil.generateSignature(map, config.getKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("sign", sign);
		
		return map;
		
	}
	 
	/**
	 * 判断预支付是否成功
	 * @param unifiedOrder
	 */
	 public void ss (Map<String, String> unifiedOrder){
		 	String returnCode = unifiedOrder.get("return_code");
	        String resultCode = unifiedOrder.get("result_code");
	        String returnMsg = unifiedOrder.get("return_msg");
	        String errCodeDes = unifiedOrder.get("err_code_des");
	        if(!returnCode.equals("SUCCESS")) throw new MyException(returnMsg);//支付失败了抛异常
	        if(!resultCode.equals("SUCCESS")) throw new MyException(errCodeDes);
	        System.out.println(returnCode);
	        System.out.println(resultCode);
	 }
	 
	 /**
	  * 获取用户ip
	  * @param request
	  * @return
	  */
	 public static String getIp(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("X-Real-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        if (ip == null) {
	            ip = "";
	        }
	        if ("127.0.0.1".equals(ip) || "localhost".equalsIgnoreCase(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
	            try {
	                InetAddress addr = InetAddress.getLocalHost();
	                ip = addr.getHostAddress();
	            } catch (Exception e) {
	                return "";
	            }
	        }

	        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	        if (ip != null && ip.length() > 15) { // "***.***.***.***".length() = 15
	            if (ip.indexOf(",") > 0) {
	                ip = ip.substring(0, ip.indexOf(","));
	            }
	        }
	        return ip;
	    }
	 /**
	  * 支付回调
	  * @param xml
	  */
	 public String payCall(HttpServletRequest request) throws IOException{

		 	BufferedReader reader = request.getReader();
	        String line = "";
	        String xmlString = null;
	        StringBuffer inputString = new StringBuffer();
	        while ((line = reader.readLine()) != null) {
	            inputString.append(line);
	        }
	        xmlString = inputString.toString();
	        request.getReader().close();
	        System.out.println("-------------------------------------------------------------------------");
	        System.out.println("微信返回数据："+xmlString);
	        
	        Map<String, String> map = readStringXmlOut(xmlString);
			 if(!"SUCCESS".equals(map.get("return_code").toString())) throw new MyException(map.get("return_msg"));
			 if(!"SUCCESS".equals(map.get("result_code").toString())) throw new MyException(map.get("err_code"));
			 System.out.println("map:--"+map.toString());
//			 this.s1(map);业务处理
			 return map.get("result_code").toString();
	 }
	 
	 /**
	  * 回调业务处理，修改订单状态
	  * @param map
	  */
/*	 public void s1(Map<String, String> map){
		 List<ShopncOrders> orders = orderService.findByOrderIds(map.get("attach"));
		 String batchNum = String.valueOf(map.get("out_trade_no").toString());
		 String time_end = String.valueOf(map.get("time_end").toString());
		 String subOpenid = String.valueOf(map.get("openid").toString());
		 for (ShopncOrders order : orders) {
			 order.setPayment_code("wxpay-b");
			 order.setTrade_no(map.get("transaction_id"));
			 payService.orderComplete(order, batchNum, time_end, subOpenid);
			
		}
		 
	 }*/
	 
	 /** 
	     * @description 将xml字符串转换成map 
	     * @param xml 
	     * @return Map 
	     */  
	    public static Map<String,String> readStringXmlOut(String xml) {  
	        Map<String,String> map = new HashMap<String,String>();  
	        Document doc = null;  
	        try {  
	            doc = DocumentHelper.parseText(xml); // 将字符串转为XML  
	            Element rootElt = doc.getRootElement(); // 获取根节点  
	            List<Element> list = rootElt.elements();//获取根节点下所有节点  
	            for (Element element : list) {  //遍历节点  
	                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value  
	            }  
	        } catch (DocumentException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return map;  
	    }  
	    
	    /**
	     * 微信退款
	     * @param pay_sn 商户订单号	（out_trade_no）
	     * @param order_id 订单id主要用来生成退款号，我这里先随便写一个
	     * @return
	     */
	    public Map<String, String> wXReturn(String pay_sn,int order_id){
	    	order_id=(int) ((Math.random()*100));
			Map<String, String> map = new HashMap<String, String>();
			map.put("out_trade_no", pay_sn);
			map.put("out_refund_no", this.batchNo(order_id));
			map.put("total_fee", "1");
			map.put("refund_fee", "1");
	        map.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");//可用余额退款
			
			Map<String, String> refund=null;
			try {
				WXPay wxpay = new WXPay(config);
				refund = wxpay.refund(map);
				System.out.println("退款微信返回："+refund.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return refund;
		
	    }
	    
	    /**
	     * 生成退款号给微信
	     * @param order_id
	     * @return
	     */
		public String batchNo(int order_id) {
			StringBuffer sBuffers = new StringBuffer();
			Calendar cal = Calendar.getInstance();
			int Y = cal.get(Calendar.YEAR);
			int M = cal.get(Calendar.MONTH) + 1;
			int d = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			sBuffers.append(Y).append(M).append(d).append(hour).append(minute)
					.append(second).append(order_id);
			String batch_no = sBuffers.toString();
			return batch_no;

		}
}
