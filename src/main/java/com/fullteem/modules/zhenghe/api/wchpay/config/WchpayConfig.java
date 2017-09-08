package com.fullteem.modules.zhenghe.api.wchpay.config;

public class WchpayConfig {
	
	/*//公众账号ID
	public static final String appid = "wxd678efh567hg6787";
	
	//商户号
	public static final String mch_id = "1230000109";
	
	//商户密钥
	public static final String key = "zhenghe666goods940903";*/


	//公众账号ID
	public static final String appid = "wx017e2e3ae3a1e866";

	//商户号
	public static final String mch_id = "1487079832";

	//商户密钥
	public static final String key = "4AVKN3dF4DR9VBppNxKAtS1GTK1dNo05";

	public static String create_ip = "127.0.0.1";

	public static String trade_type = "APP";

	//统一下单接口
	public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			
	//通知地址
	public static final String notify_uri = "/api/wchpay/WchpayNotifyController/wchpayNotify";
	
	// 字符编码格式 目前支持utf-8
	public static final String charset = "utf-8";
}
