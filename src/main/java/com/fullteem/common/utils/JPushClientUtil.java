package com.fullteem.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;



/**
 * 
 * @Title: JpushClientUtil.java
 * @Package com.fullteem.web.utils
 * @Description: 极光推送
 * @author YuanPan
 * @date 2014-12-18上午9:55:25
 * @version V1.0
 */
public class JPushClientUtil {

	private final static Logger log = Logger.getLogger(JPushClientUtil.class);

	private static ResourceBundle res = ResourceBundle.getBundle("fullteem");
	private static final String MASTER_SECRET = "masterSecret";
	private static final String APP_KEY = "appKey";
	private static String appkey;
	private static String masterSecret;

	static {
		masterSecret = res.getString(MASTER_SECRET);
		appkey = res.getString(APP_KEY);
	}

	public static JPushClient getJPushClient() {
		log.debug("masterSecret : " + masterSecret);
		log.debug("appkey : " + appkey);
		JPushClient jPushClient = new JPushClient(masterSecret, appkey);
		return jPushClient;
	}
	
	public static void send(String title , String content , String...tags){
		try {
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
                    .setAudience(Audience.alias(tags))
	                .setMessage(Message.newBuilder()
	                        .setTitle(title)
	                        .setMsgContent(content)
	                        .build())
	                .build();
			
			PushResult result = getJPushClient().sendPush(payload);
			log.info("Got result - " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			log.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			e.printStackTrace();
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
			log.info("Msg ID: " + e.getMsgId());
		}
	}
	public static void send(String title , String content){
		try {
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
					.setAudience(Audience.all())
					.setNotification(Notification.alert(title))
					.setMessage(Message.newBuilder()
							.setTitle(title)
							.setMsgContent(content)
							.build())
							.build();
			
			PushResult result = getJPushClient().sendPush(payload);
			log.info("Got result - " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			log.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			e.printStackTrace();
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
			log.info("Msg ID: " + e.getMsgId());
		}
	}

	public static void send(String title , String content ,Map<String, String> extras ,List<String> tags){
		try {
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.all())//设置接受的平台					
					//.setOptions(Options.newBuilder().setApnsProduction(true).build())//生产环境
					.setOptions(Options.newBuilder().setApnsProduction(false).setTimeToLive(259200).build())//开发环境 离线3天					
					//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                    .setAudience((tags!=null&& tags.size()>0) ? Audience.alias(tags) : Audience.all())
                   
                    .setNotification(
                               Notification
                               .newBuilder()
                               .setAlert(title)
                               .addPlatformNotification(
                            		   AndroidNotification
                            		   .newBuilder()
                            		   .setTitle(title)
                            		   .addExtras(extras)
                            		   .build())
                               .addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build()).build())
                               
	                .setMessage(
	                		Message
	                		.newBuilder()
	                		.setTitle(title)
	                		.setMsgContent(content)
	                		.addExtras(extras)
	                		.build()
	                		)
	                		
	                .build();
			
			PushResult result = getJPushClient().sendPush(payload);
			log.info("Got result - " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			log.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			e.printStackTrace();
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
			log.info("Msg ID: " + e.getMsgId());
		}
	}
	public static void main(String[] args) throws Exception {
		Map<String, String> extras = new HashMap<String, String>();	
        
        extras.put("type", "1");
		List<String> tags=new ArrayList<String>();
		tags.add("63");
		//JPushClientUtil.send("测试离线推送~" + DateUtil.getNowPlusTime()+"---","测试离线推送.......",extras,tags);
	}
}
