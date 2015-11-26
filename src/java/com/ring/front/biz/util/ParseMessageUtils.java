package com.ring.front.biz.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @desc XML解析工具类
 * @author GANCHUNGEN
 * @since 2014-10-16
 */
public class ParseMessageUtils {
	protected final static Log logger = LogFactory.getLog(ParseMessageUtils.class);

	/**
	 * @desc 将XML数据解析成Map[LIST[ {MAP},{MAP} ]LIST[ {MAP} ] ] 的结构 XML结构中有中文时需注意编码
	 * @param xmlData
	 * @return Map
	 * @throws DocumentException
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Map parseXML(String xmlData) throws DocumentException {
		Map dataMap = new HashMap();
		if (xmlData == null) {
			return dataMap;
		}
		List dataList = new ArrayList();
		InputStream stream = null;
		InputStreamReader streamReader = null;
		try {
			stream = new ByteArrayInputStream(xmlData.getBytes());
			streamReader = extracted(stream);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(streamReader);
			Element rootElt = document.getRootElement();
			parseElement(rootElt, dataList, dataMap);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
			if (streamReader != null) {
				try {
					streamReader.close();
				} catch (IOException e) {
				}
			}
		}
		return dataMap;
	}

	private static InputStreamReader extracted(InputStream stream) {
		InputStreamReader streamReader;
		streamReader = new InputStreamReader(stream/* ,"GBK" */);
		return streamReader;
	}

	/**
	 * 
	 * @desc 递归解析XML Element
	 * @param rootElt
	 * @param dataList
	 * @param dataMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void parseElement(Element rootElt, List dataList, Map dataMap) {
		if (rootElt == null) {
			return;
		}
		List list = rootElt.elements();
		for (Iterator itor = list.iterator(); itor.hasNext();) {
			Element elt = (Element) itor.next();
			if (elt != null) {
				String eltName = elt.getName();
				if ("element".equals(eltName.toLowerCase())) {
					Map eleMap = createMapByElement(elt);
					if ("element".equals(eltName.toLowerCase())) {
						if (dataList != null) {
							dataList.add(eleMap);
						}
					}
					parseElement(elt, dataList, eleMap);
					dataMap.put("ELEMENTS", dataList);
					continue;
				}
				dataMap.put(elt.getName(), elt.getText());
				parseElement(elt, dataList, dataMap);
			}
		}
	}

	/**
	 * @category 创建map内容
	 * @param element
	 * @return Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map createMapByElement(Element element) {
		Iterator eleItor = element.elementIterator();
		Map eleMap = new HashMap();
		while (eleItor.hasNext()) {
			Element eleEle = (Element) eleItor.next();
			eleMap.put(eleEle.getName(), eleEle.getText());
		}
		return eleMap;
	}

	public static void main(String args[]) throws Exception {	
		String send = "<?xml version=\"1.0\" encoding = \"GBK\"?>" +
				"<BEDC>" +
					"<Message>" +
						"<commHead>" +
							"<tranCode>交易代码:0001</tranCode>" +
							"<cifMaster>网银客户号</cifMaster>" +
							"<entSeqNo>企业财务系统自己产生的流水号。每天唯一</entSeqNo>" +
							"<tranDate>操作日期</tranDate><tranTime>操作时间</tranTime>" +
							"<retCode>返回码</retCode><entUserId>操作员</entUserId>" +
							"<password><![CDATA[操作密码]]></password>" +
						"</commHead>" +
					    "<Body>" +
						 	"<account>账号</account>" +
						"</Body>" +
					"</Message>" +
				"</BEDC>";
      String msg1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><REQUEST><tranCode>100917</tranCode><ELEMENTS><ELEMENT><tempID>EQIANBAOTEMP0003</tempID><msgID>90500000040713152</msgID><msgType>ios</msgType><deviceID>18b0a82baf99e92acfd5a1ee1b434a008b67ff51958cca96ba5e0f7e92c885ec</deviceID><param>birder : [色]</param><msgCount>1</msgCount></ELEMENT><ELEMENT><tempID>EQIANBAOTEMP0003</tempID><msgID>90500000040713153</msgID><msgType>ios</msgType><deviceID>d3ecc3b973741fbad6c688f3a801d10af676b2a8169f7b1c8d874ef3a6f44b12</deviceID><param>张春贵 : 收到</param><msgCount>1</msgCount></ELEMENT></ELEMENTS><batchNo>fcb3a807-8b58-489f-bba9-0afd1483f986</batchNo><sysID>hm-core</sysID></REQUEST>";
      String back ="<?xml version=\"1.0\" encoding = \"GBK\"?>" +
		"<BEDC>" +
			"<Message>" +
				"<commHead>" +
					"<tranCode>交易代码:0001</tranCode>" +
					"<cifMaster>网银客户号</cifMaster>" +
					"<entSeqNo>企业财务系统自己产生的流水号。每天唯一</entSeqNo>" +
					"<tranDate>操作日期</tranDate>" +
					"<tranTime>操作时间</tranTime>" +
					"<retCode>返回码</retCode>" +
					"<entUserId>操作员</entUserId>" +
					"<password><![CDATA[操作密码]]></password>" +
				"</commHead>" +
				"<Body>" +
					"<account>账号</account>" +
					"<balSign>余额币种</balSign>" +
					"<balance>可用余额</balance>" +
					"<balance>冻结金额</balance>" +
					"<lastDate>最后交易日期</lastDate>" +
				"</Body>" +
			"</Message>" +
		"</BEDC>";
		Map hmap = parseXML(back);
		System.out.println("节点个数："+hmap.size());
		System.out.println(hmap);
     	System.out.println("lastDate==="+hmap.get("lastDate"));
	}
}