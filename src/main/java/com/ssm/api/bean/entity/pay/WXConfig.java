package com.ssm.api.bean.entity.pay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.util.IOUtils;

import com.github.wxpay.sdk.WXPayConfig;

@Component
public class WXConfig implements WXPayConfig{
//公司账号我也不知道哪一个。。。。
//	private static final String appId="wx68b2be0c7309c5bd"; 
	private static final String appId="wx04043369f24ed24a"; 
//	private static final String mchId="1489812322"; 
	private static final String mchId="1307057701"; 
	private static final String key="F33F1FD0BBCE051A1BF4FCD9D3788F10"; 
	public static final String subMchid="1489890612"; 
	public static final String subAppId="wx04043369f24ed24a"; 
	
	//在项目的resources包下的application.properties文件中配置wechat.sl.certPath=/cert/apiclient_cert-sl.p12
    @Value("${wechat.sl.certPath}")//证书
    private String certPath;

    private byte[] certData;

    /*public void afterPropertiesSet() throws Exception {
        this.certData = readResource(certPath);
    }*/
	
	@Override
	public String getAppID() {
		// TODO Auto-generated method stub
		return appId;
	}

	@Override
	public String getMchID() {
		// TODO Auto-generated method stub
		return mchId;
//		return subMchid;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public InputStream getCertStream() {
		this.certData = readResource(certPath);//退款的时候加的，支付本来是没有的
		return new ByteArrayInputStream(this.certData);
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		// TODO Auto-generated method stub
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		// TODO Auto-generated method stub
		return 10000;
	}

	
	
	/**
     * 读取资源
     */
    public static byte[] readResource(String resource){
        byte[] buffer = null;
        try {
            InputStream certStream = IOUtils.class.getResourceAsStream(resource);
            buffer = new byte[certStream.available()];
            certStream.read(buffer);
            certStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
