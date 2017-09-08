package com.fullteem.modules.zhenghe.api.wchpay.util;

import java.util.SortedMap;

import com.fullteem.modules.zhenghe.api.wchpay.config.WchpayConfig;
import com.fullteem.modules.zhenghe.api.wchpay.sign.MD5Service;

public class WchpayCore {
	
	/**
	 * 签名校验
	 * @param resultMap
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(SortedMap<String, Object> resultMap) throws Exception {
		boolean result = false;
		String sign1 = (String)resultMap.get("sign");
		resultMap.remove("sign");
		String sign2 = MD5Service.createSign(resultMap);
		//签名校验
		if(sign1.equals(sign2)){
			result = true;
		}
		return result;
	}

	/**
	 * 调用统一下单接口
	 * @param postXml
	 * @return
	 * @throws Exception
	 */
	public static String callUnifiedorder(String postXml)throws Exception{
		
		return HttpUtil.postXmlToServer(WchpayConfig.pay_url, postXml,WchpayConfig.charset);
	}
	
}
