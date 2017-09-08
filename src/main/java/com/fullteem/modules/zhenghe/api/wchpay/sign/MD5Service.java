package com.fullteem.modules.zhenghe.api.wchpay.sign;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.fullteem.modules.zhenghe.api.wchpay.config.WchpayConfig;

public class MD5Service {
	
	/**
	 * 签名函数
	 * @param sortedMap
	 * @return
	 * @throws Exception
	 */
	public static String createSign(SortedMap sortedMap) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<Map.Entry> es = sortedMap.entrySet();
		Iterator<Map.Entry> it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = it.next();
			String k = (String) entry.getKey();
			String v = entry.getValue() != null ? entry.getValue().toString() : null;
	
			if (v !=null && !"".equals(v.trim())) {
				sb.append(k + "=" + v + "&");
			}
		}
		
		//加上商户密钥
		sb.append("key=" + WchpayConfig.key);
		
		return MD5Service.encryptString(sb.toString());
	}
	

	/**
	 * MD5加密字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encryptString(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] md5 = digest.digest(str.getBytes("UTF-8"));
		StringBuffer md5StringBuffer = new StringBuffer();
		String part = null;
		for (int i=0;i<md5.length;i++) {
			part = Integer.toHexString(md5[i] & 0xFF);
			if (part.length()==1) {
				part = "0"+part;
			}
			md5StringBuffer.append(part);
		}
		
		//将得到的字符串所有字符转换为大写
		return md5StringBuffer.toString().toUpperCase();
	}
	
}

