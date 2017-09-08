package com.fullteem.modules.zhenghe.api.utils;

import java.util.HashMap;
import java.util.Map;

public class ZhengheUpload extends AbstractFileUpload {
	
	public static final String MEDICALHISTORY = "/zhengheImage/medicalHistory/";		//患者病历
	public static final String COURSEDISEASE = "/zhengheImage/courseDisease/";		//患者病程
	public static final String LIFE = "/zhengheImage/life/";							//生活日志
	public static final String UNKNOWN = "/zhengheImage/unknown/";					//没有指定目录的放这里
	private int choice;
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 选择上传文件要保存的目录:1->患者病历目录;2->患者病程目录;3->生活日志目录;</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月11日</br>
	 * @param choice
	 */
	public void choosePath(int choice){
		this.choice = choice;
	}
	
	@Override
	public String getPath() {
		String path = null;
		switch(choice){
			case 1:path = MEDICALHISTORY ;  break;
			case 2:path = COURSEDISEASE ; 	break;
			case 3:path = LIFE; 			break;
			default:path = UNKNOWN; 		break;
		}
		return path;
	}

	@Override
	public Map<String, String> getAllowExtMap() {
		Map<String, String> allowExtMap = new HashMap<String, String>(3);
		allowExtMap.put("jpg", null);
		allowExtMap.put("png", null);
		allowExtMap.put("gif", null);
		allowExtMap.put("bmp", null);
		return allowExtMap;
	}
	
	

}
