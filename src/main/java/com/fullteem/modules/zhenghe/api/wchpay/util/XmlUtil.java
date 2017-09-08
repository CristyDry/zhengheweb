package com.fullteem.modules.zhenghe.api.wchpay.util;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 这个类只适用于简单的xml格式转换
 * @author Administrator
 *
 */
public class XmlUtil {

	
	/**
	 * XML格式的字符串转SortedMap
	 * @param strXml
	 * @return
	 * @throws DocumentException
	 */
	public static SortedMap<String, Object> createMap(String strXml) throws DocumentException{
		Document document = DocumentHelper.parseText(strXml);
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
		Element root = document.getRootElement();
		Iterator iterator = root.elementIterator();
		while(iterator.hasNext()){
			Element element = (Element)iterator.next();
			sortedMap.put(element.getName(), element.getText());
		}
		return sortedMap;
	}
	
	
	/**
	 * SortedMap转XML格式的字符串
	 * @param sortedMap
	 * @return
	 */
	public static String createXml(SortedMap sortedMap){
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		document.setRootElement(root);
		Set keySet = sortedMap.keySet();
		for(Object key:keySet){
			String value = sortedMap.get(key).toString();
			Element element=DocumentHelper.createElement(key.toString());
			element.setText(value);
			document.getRootElement().add(element);
		}
		return document.asXML();
	}
	
}
