package com.ssm.api.bean.entity.pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016081600254622";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCKzzBQA/3uybyLR3Xka+LXFgZLJB+xvrJcHS5tQWTe4FNvSIkZhuMXvBeQceS8qe0A7/hnqxpY5gui/DCK7VJlsjBuWHdDzZ698SwdtSPcw+Bkjc2+OSYPAj0/+y6vHeJVbAanZ/AkCAZxB5vtJdEfhiL8JfGUJ8hSjFD3mN23wMBhZn2HMndmLnpDWI8meYwg0/EzCPdUSwLtgJQYz6QBj5hRkgVGKxBN0h4vprugcbb1wFnSYe4Lb5VqK/TQHrPYSEUnE+5rR/aQYSnhjnaRosJc44IOW/aFLH1iPXY634zNPltQ6UBSZJBgzL8wYX8++lbEz8TOGaJ/kkNWU6U7AgMBAAECggEAGcwv2m8jfoGXvPrm+jDi076QJN7t1Qbv9xalTkDdEl72orQEZu4evoaCiqHgNQAPwCDZNcsi9mqqtvvPucIpkiTvw1JFuCA65GoyHMNRivIMfiOsnTHZ1OhBoeZ44qrXKbEN+4bhppCnQdJ8NDLXDtjLH7mdkD9kh5L0aU/LxR6Tj0e6XEgrBjnMBdBAzZLK9LqKdV4yzTiW2hHMGLZsQhWwcYEIlqMeoXYaOZPtgENAREm/DOODy6pkX8trBJkVII89TocAN/Hw8aSkU/VBaflv3AHpgPKQaNrVLu+pyK94o3a6j8pcIx3urJ/6qZog30oowK/nqSQaw6zNr0D7YQKBgQC/ALXwewkiViwe+sBlqWxf4fypYUG6m4Lc/wbJJUjvonBevbyKSvuPbfncHIWnvq6iEuzZ5qVkBVOtnKar+ApXYZOIbeoNNVUxrIpveWqMWiBJUUH+BGI2tv1VcmmFnvnt5RqQHcJ+NCVmrAIwpqxjb46r8zq/cgAvG3p+SDVOhQKBgQC6C5+eMrKNgj8CbWmH8oJGO93weGVKGftuQwnuYIPTYjEURC+64g3ZDdEeWcnqAojO8pblwtLNzaaKLzeK7KzU/SE8OUiCJa1Ohmaz1ihXhuXkSpBVUZqNCbko6JRzP/rHc7aKlFHOBn6vEzuQm0MN/hndM8PB/vPJ0mecHFrQvwKBgQCRZFoaX525XH0ja97OxCPx7NVmndjp0nzP+vVQYihd+XmuNKHzZ1aMfLuouLFo2jaWuu8+g1q8g22KB2e8GgjNMUKbpId4XHOoZ13Ns0/O3MAc71TbpryYR70ZsQjwJO69DxukPnMRnGIoAKF+JTGuU3n4HCNJHd9T/IfG3vJs/QKBgQCC40iRyMMbkWV49LbJAREsDlRPKLTCFFNyuFFVVquH3M4AnxQhW4Y4jWLxAmRucQgE6GDHFnCuf59KGnvuDiJt2gQ21WowvgcWdrHslZ9eLLayDM9MZxPfY9bTdAV0q69oAP2Ms1p2ItWfoF1ImCLtxjY2DIQNiKvE8nUYPE6cCwKBgCJcq7ckXAvHqHcSd6hdztNcmOFdZ+jLBJa1Xm3/T5sm+sZMPYXsa9jrvWv9KDRcDYiQ9JvnWpbiQ3KfTHycWB6UrXNC+7boC3WasZJxs3o0FHZhhyCLoZ3n7jm1tpd8KqMYMyNqBKbhg4qmzhdKEXqq6JiUN21OC+G2a9S2Ik/q";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxWPG8kzVUTGN0KMfqkM1tA3cPPw43GURWJ9w2+o16a8QgiuRSevIIOigmbGvxDQ3AN5QLblgQQODDbF2kfWZtj0+yUWdZUybzLNsdtIR3HAyUTgLb64hu/5stILCt6JagBWJ9pV0r8ztcGKHyOd4hwqn1/GRhmsnzRam+l0oEMXxTrQ+Z5irywuWfA+JBiPbyvGqgZLQm6BYI6LsKguQCUQn7hu+sl/TvcId/szuCJPaF5MB8RR35IDXE9YL+moXJUTEc42sCjunY05VmMWa4qtt+6WHNvlHJ//AZeZDIgKgwdJ+M5ua2R9Dd224mqSRCR/SowIkDDyIojTL0a2UlwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8090/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://localhost:8090/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
//	public static String return_url = "http://localhost:8090/ssm/return_url";
	public static String return_url = "http://www.baidu.com";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

