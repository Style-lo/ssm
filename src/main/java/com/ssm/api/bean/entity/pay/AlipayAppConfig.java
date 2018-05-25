package com.ssm.api.bean.entity.pay;

public class AlipayAppConfig {
    // 商户appid
    public static String APPID = "2016041101287231";
    // 私钥 pkcs8格式的

    public static String RSA_PRIVATE_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAK4iw7E9oMa+l9qpQTMB/qL6QQG1vUB1+cj+bHLaqMKpDZuqEtoeCcwBpj8oenHTX4fVfZ784tilQh3wJ9Bt2RXWrJtM7Tfzm9t5iOV/+1etaWfE6nFKw52oqiVdkuAnAwfUGWbYFLxZfQwDSmGpLExo7TAdFZprR2FWVT8AXIOTAgMBAAECgYEAl3ysy2L2nA8wYcv+aVZh4/xbUxZ8hjhbzYvwYlZwm6+fo/znm5KoKS6CJs2a/6zHnY6PUyFiYuAZTZwftsXpJBPT0liJBgBjPGot2cl/ikHTYlPjVMxUxJqF3NVNx8xRBIHyiMTmxh33z3uj3HqHZXmuUoaDEa3VqXxdRYN6K0ECQQDbhFqeLOwvbjBpiEhlnhHZ7c/mKuIvCZQQ2/KNcC0okn/67IHZmEKttHWqSJ+tBorgi80lIBD3eszRX6a6QNAhAkEAyxObHkOCwq/Dhxu9OW47vU2DmsbwFbLEo95ZII+yzOZlEmX5qj9gglNoricu3bxjix7PNc8b4Nnn2A5gdjVtMwJBAITO5IxCHXrsrXmLrFFeeupgiKF2XHWc0+oHSA3uSkP7rlY/dKgTCHRTc8TcP07NYIRN0IWfiuRPdU9PEW4p/EECQQDCXC1mNa78rkQxF3dvc/VxJLMEe8pkughB3hjndSDnwsqRZVAihS3VDgduRsowJpIPIJmpImPIPEYNHxVrjY7JAkEAsdQPhNSr94Mm2ZE8EHr2m+YHhha+5eSPJdNTMQY8nlGczl8eQfdjCam9QZkSuuPWUvc6UyC/yYF0FZbn+5UHHw==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://商户网关地址/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://商户网关地址/return_url.jsp";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥			
    public static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA";
}
