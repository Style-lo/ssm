package com.ssm.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ssm.api.bean.entity.AlipayConfig;
import com.ssm.api.bean.request.AliPayRequest;
import com.ssm.api.service.AliPCPayService;

@Controller
@RequestMapping("api/v1/alipay")
public class AliPayController {

	@Autowired
	AliPCPayService aliPCPayService;
	/**
     * 获取订单数据接口
     * @param request
     * @param response
     * @throws AlipayApiException 
     * @throws IOException 
     */
    @RequestMapping("viewOrder")
    public void viewOrder(HttpServletRequest req, Model mod, HttpServletResponse rep,
            @RequestParam(value = "goodId", required = true)Integer goodId) throws  IOException, AlipayApiException{

      //获得初始化的AlipayClient
    	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
    	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new Date().getTime()+"li";
        //付款金额，必填
        String total_amount = "1";
        //订单名称，必填
        String subject = "支付测试";
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
                + "\"total_amount\":\""+ total_amount +"\"," 
                + "\"subject\":\""+ subject +"\"," 
                + "\"body\":\""+ body +"\"," 
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        System.out.println(alipayRequest.getBizContent().toString());
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        rep.setContentType("text/html;charset=" + AlipayConfig.charset);
        rep.getWriter().write(result);//直接将完整的表单html输出到页面
        rep.getWriter().flush();
        rep.getWriter().close();
    }
    
    /**
     * 回调路径return_url
     * @param request
     * @param response
     * @throws AlipayApiException 
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("return_url")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException{
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,AlipayConfig.sign_type);
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            request.setAttribute("out_trade_no", out_trade_no);
            request.setAttribute("trade_no", trade_no);
            request.setAttribute("total_amount", total_amount);

            System.out.println("out_trade_no :"+out_trade_no);
            System.out.println("trade_no :"+trade_no);
            System.out.println("total_amount :"+total_amount);
            
            //支付成功，做业务处理
        }else{
            request.setAttribute("reason", "验签失败");
        }
        request.setAttribute("signVerified", signVerified);
        return "loginOK";
    }
    

    @RequestMapping("aliReturn")
    public void aliReturn() throws AlipayApiException{
//    	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key");
    	//实例化客户端
  	 AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,AlipayConfig.sign_type); 
  	 AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

  	 //使用Map也行，把支付宝需要的参数的已json格式传过去就行
     Map<String, String> data = new HashMap<String, String>();
//     data.put("out_trade_no", "2018022621001004920200414543");
     data.put("out_trade_no", "1519628929344li");
     data.put("refund_amount", "1");
     request.setBizContent(JSON.toJSONString(data));
//将参数转换成Json格式传给支付宝
//     request.setBizContent(JSON.toJSONString(alidata));
     AlipayTradeRefundResponse response = client.execute(request);
     System.out.println(response.toString());
     System.out.println(response.getBody());
     if(response.isSuccess()){
     System.out.println("退款成功");
     } else {
     System.out.println("退款失败");
     }
	
	}
    
    
    @RequestMapping("aliPays")
    public void aliPays(HttpServletResponse rep) throws IOException{
    	String pcPay = aliPCPayService.pcPay(new AliPayRequest(getOrderIdByUUId(), "1", "商品标题：腾讯充值", "商品描述：***账号充值200qb"));
    	 rep.setContentType("text/html;charset=" + AlipayConfig.charset);
         rep.getWriter().write(pcPay);//直接将完整的表单html输出到页面
         rep.getWriter().flush();
         rep.getWriter().close();
    	
    }
    
	/**
	 * 生成订单号
	 * @return
	 */
	public static String getOrderIdByUUId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String format = sdf.format(new Date());
		int machineId = (Integer.valueOf(format) % 9)+1;
//		int machineId = 9;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}
}
