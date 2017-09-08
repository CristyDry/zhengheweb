package com.fullteem.modules.zhenghe.api.utils;

import org.apache.log4j.Logger;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class MiPushUtil {
	
	private static Logger logger = Logger.getRootLogger();
	
	public static void sendGetOutMessage(String regId)throws Exception{
		String title = "下线通知";
		String messagePayload = "您的账户已在另一台设备上登录,当前账户已下线。如发现账户存在异常请及时修改密码";
		String description = "下线通知";
		sendMessage(regId, title, messagePayload, description);
	}
	
	public static void main(String[] args) throws Exception {
		//安卓
		//sendMessage("d\\/\\/igwEhgBGCI2TG6lWqlOW5vByqJZkt4N2mg4hf11OSMchtw99Rf+ooTDfpGa5sjMtA+zqhFctf83t8ko966084jtx0qAVxLk++aKSSofw=","测试","小米推送测试","小米推送测试");
		//苹果
		//sendMessage("d//igwEhgBGCI2TG6lWqlJBjFhhTTrwmefoz+RrHd3PxtUkBEaw6AbXvEowiRojYA6Gv7V5ZTqYUnTMdf+kha/3W86bLyeVlXFrqviujI40=","测试","小米推送测试","小米推送测试");
		
		//sendGetOutMessage("d//igwEhgBGCI2TG6lWqlJBjFhhTTrwmefoz+RrHd3PxtUkBEaw6AbXvEowiRojYA6Gv7V5ZTqYUnTMdf+kha/3W86bLyeVlXFrqviujI40=");
	}
	
	
	/*
	 * 返回推送结果
	 */
	public static boolean sendMessage(String regId,String titl,String msg,String desc){
		 boolean result = false;
		 //正式环境下使用push服务
	     Constants.useOfficial();
	     //测试环境下使用push服务
	     //Constants.useSandbox();
	     
	     try {
	    	 Result aResult = sendMessageToAndroid(regId, titl, msg, desc);
	    	 Result iResult = sendMessageToIOS(regId, titl, msg, desc);
	    	 
	    	 String aResultId = aResult.getMessageId();
	    	 String iResultId = iResult.getMessageId();
	    	 
	    	 if(aResultId==null&&iResultId==null){
	 			logger.debug("安卓与苹果均推送失败.....推送失败~~~推送失败~~~推送失败~~~重要的事情说三遍");
	 			logger.debug("安卓："+aResult.getReason());
	 			logger.debug("苹果："+iResult.getReason());
	 			
	    	 }else{
	    		result = true;
	 			logger.debug("推送成功~~~");
	    	 }
		} catch (Exception e) {
			logger.debug("发生异常.....推送失败~~~推送失败~~~推送失败~~~重要的事情说三遍");
			e.printStackTrace();
		}
	     
	     return result;
	}
	
	/*
	 * 推送消息给IOS设备
	 */
	private static Result sendMessageToIOS(String regId,String titl,String msg,String desc)throws Exception{  
		Sender sender = new Sender(Data.IS.getValue());
		Message message = new Message.IOSBuilder()
		        .description(desc)
		        .soundURL("default")    // 消息铃声
		        .badge(1)               // 数字角标
		        .category("action")     // 快速回复类别
		        .extra("message", msg)  // 自定义键值对
		        .extra("title", titl)	// 自定义标题
		        .build();
		Result iResult = sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，0为不重试。
		return iResult;
	}

	
	/*
	 * 推送消息给Android设备
	 */
	private static Result sendMessageToAndroid(String regId,String titl,String msg,String desc)throws Exception{
	     Sender sender = new Sender(Data.AS.getValue());
	     Message message = new Message.Builder()
		         .title(titl)
		         .description(desc).payload(msg)
		         .restrictedPackageName(Data.AP.getValue())
		         .passThrough(0)  //消息是否使用透传方式1->使用,0->不使用
		         .notifyType(1)     // 使用默认提示音提示
		         .build();
	     Result aResult = sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，0为不重试。
	     return aResult;
	}
	
	
	/*
	 * 密钥区
	 */
	enum Data{
		
		AS("Kr4FF5qmEzle/w01LS1D6g=="),AP("com.fsszhyy.zh"),IS("+cC7t3Ou/wwvlPvbu1kcPA=="),IP("com.u1kj.ZHYL");
		
		private final String value;
		 
		private Data(String value) {
		     this.value = value;
		}
		 
		public String getValue() {
		     return value;
		}
		
	}
	
}
