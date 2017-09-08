package com.fullteem.modules.zhenghe.api.utils;

import org.apache.commons.lang3.StringUtils;

public class Tool {
	
	/*
	 * liqihua
	 * 判断多个字符是否为空，如果包含空，就返回true
	 */
	public static boolean strBlank(String ... strs ){
		boolean bool = false;
		for(String str : strs){
			if(StringUtils.isBlank(str)){
				return true;
			}
		}
		return bool;
	}
	
	/*
	 * liqihua
	 * 判断多个字符是否为数字字符串，如果包含非数字字符串，就返回true
	 */
	public static boolean isNotNumber(String ... strs){
		boolean bool = false;
		for(String str : strs){
			if(!StringUtils.isNumeric(str)){
				bool = true;
			}
		}
		return bool;
	}
	
	/*
	 * liqihua
	 * 判断某个String变量是否存在于String可变参数中，如果不是，返回true
	 */
	public static boolean strNoIn(String str,String ... strs){
		for(String s : strs){
			if(str.equals(s)){
				return false;
			}
		}
		return true;
	}
	
}
