/*
 * @(#)DataBaseUtil.java 2011-2-24
 * 
 * {Stay Replace Description}. All rights reserved.
 */
package com.fullteem.modules.zhenghe.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * <p>数据库工具类</p>
 * <p>Date: 2011-2-24 下午04:18:48
 * @author 何东
 * @version 1.0
 * @since JDK1.6
 * */
public class DataBaseUtil {
	
	/**
	 * 生成数据编号标示码, 值: 随机数(0-9).
	 * */
	private static final int ONLY_LONG_NUMBER_FLAG = new Random().nextInt(9);
	/**
	 * 数据并发量计数器
	 * */
	private static short currentNumberIndex = 0;
	
	/**
	 * @return 获取32位的UUID, 重复几率极低.
	 * */
	public static synchronized String getUUID32Code() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取一个唯一的Long数据
	 * @return 获取一个long数据类型这个数据可支持每一毫秒并发量 {@link Short#MAX_VALUE} 条记录,
	 * 在不同的电脑上重复概率的多少将取决于 ONLY_LONG_NUMBER_FLAG 和服务器时间.
	 * */
	public static synchronized long getOnlyLongNumber() {
		// 设置一个缓冲区来存放即将生成的数据编号并写入当前系统的编号
		StringBuffer longNumber = new StringBuffer(ONLY_LONG_NUMBER_FLAG);
		// 写入当前系统时间
		longNumber.append(System.currentTimeMillis());
		// 写入当前数据的并发量编号
		longNumber.append(currentNumberIndex++);
		// 如果超过最大并发量则重置为0
		if (currentNumberIndex > Short.MAX_VALUE)
			currentNumberIndex = 0;
		return Long.valueOf(longNumber.toString());
	}
	
//	@Test
	public void test() {
		Map<Long, Integer> testMap = new HashMap<Long, Integer>();
		int findSomeCount = 0;
		int count = 0;
		int flag = new Random().nextInt(10);
		for (int i=0; i<500000; i++) {
			StringBuffer longNumber = new StringBuffer();
			longNumber.append(flag);
			longNumber.append(System.currentTimeMillis());
			longNumber.append(count++);
			if (count == Short.MAX_VALUE)
				count = 0;
			Long testNumber = Long.valueOf(longNumber.toString());
			if (testMap.containsKey(testNumber)) {
				com.fullteem.common.utils.Log.println("有重复数据, 已记录" + testMap.get(testNumber) + "次.");
				testMap.put(testNumber, testMap.get(testNumber) + 1);
				findSomeCount ++;
			} else {
				testMap.put(testNumber, 1);
			}
			com.fullteem.common.utils.Log.println(("I:" + i + " - ") + Math.abs(Long.valueOf(System.currentTimeMillis())));
		}
		com.fullteem.common.utils.Log.println("共有：" + findSomeCount + "次重复！");
	}

}
