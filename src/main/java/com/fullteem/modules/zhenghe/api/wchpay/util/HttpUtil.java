package com.fullteem.modules.zhenghe.api.wchpay.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpUtil {
	
	private static HttpClient client = new HttpClient();
	
	/**
	 * 接收格式为xml的post返回结果
	 * @param request
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getResultFromServer(HttpServletRequest request,String encoding) throws Exception{

        String resultXml = getResultStr(request.getInputStream(),encoding);
        
		return resultXml;
	}
	
	/**
	 * 发送格式为xml的post请求到指定url
	 * @param url
	 * @param xml
	 * @param encoding
	 * @return	xml格式的字符串
	 * @throws Exception
	 */
	public static String postXmlToServer(String url,String xml,String encoding) throws Exception{
		
		PostMethod method = new PostMethod(url);
		RequestEntity entiry = new StringRequestEntity(xml, "text/xml", encoding);
		method.setRequestEntity(entiry);
		client.executeMethod(method);
		
	    InputStream is = method.getResponseBodyAsStream();
	    String resultXml = getResultStr(is,encoding);
        
		method.releaseConnection();
		
		return resultXml;
	}
	
	
	/*
	 * 字符流
	 */
	private static String getResultStr(InputStream is,String encoding) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(is,encoding));
	    StringBuilder sb = new StringBuilder();
	    String temp = "";
        while ((temp = br.readLine()) != null) {
        	sb.append(temp);
        }
        br.close();
        return sb.toString();
	}

}
