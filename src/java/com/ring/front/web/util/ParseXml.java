package com.ring.front.web.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ring.front.web.constants.XmlKey;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;

/**
 * 
 * @description xml解析工具类
 * @author GANCHUNGEN512
 * @date 2014-7-22
 */
public class ParseXml {

	protected static Log logger = LogFactory.getLog(ParseXml.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map parseXmlStr(String xmlStr){
		Map retMap = new HashMap();
		try {
			// 创建读取配置文件的对象
			SAXReader reader = new SAXReader();
			// 开始读取配置文件
			Document doc = reader.read(new StringReader(xmlStr));
//			System.out.println(doc.getRootElement().getName());
			// 拿到根节点
			Element root = doc.getRootElement();
			String msgbody = root.getData().toString();
			String type = root.attributeValue(XmlKey.KEY_MESSAGE_TYPE);
			String loginsession = root.attributeValue(XmlKey.KEY_MESSAGE_LOGINSESSION);
			String username = root.attributeValue(XmlKey.KEY_MESSAGE_USERNAME);
			String deviceid = root.attributeValue(XmlKey.KEY_MESSAGE_DEVICEID);
			
			retMap.put(XmlKey.KEY_MESSAGE_TYPE, type);
			retMap.put(XmlKey.KEY_MESSAGE_LOGINSESSION, loginsession);
			retMap.put(XmlKey.KEY_MESSAGE_USERNAME, username);
			retMap.put(XmlKey.KEY_MESSAGE_DEVICEID, deviceid);
			retMap.put(XmlKey.KEY_MESSAGE_BODY, msgbody);
			
		} catch (XMLParseException e) {
			logger.warn("【XMLParseException occurred: " + e.getMessage() + "】");
		} catch (Exception e) {
			logger.warn("【UnknownException occurred: " + e.getMessage() + "】");
		}
//		System.out.println(retMap);
		return retMap;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><message type=\"ping\" loginsession=\"Kobe\" username=\"username\">Received successfully!</message>";
		parseXmlStr(xml);
	}

}
