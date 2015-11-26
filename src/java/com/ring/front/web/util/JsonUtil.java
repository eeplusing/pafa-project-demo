package com.ring.front.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @description:JSON数据处理工具类
 * @author ganchungen512
 * @date 2014-7-30
 */
public class JsonUtil {
	protected static Log logger = LogFactory.getLog(JsonUtil.class);
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map json2map(JSONObject json){
		Map retMap = new HashMap();
		try {
			Iterator it = json.keys();
			while(it.hasNext()){
				String key = (String) it.next();
				String value = json.getString(key);
//				System.out.println("key:"+key+"  value:"+value);
				retMap.put(key, value);
			}
		} catch (JSONException e) {
			logger.warn("【JSONException: " + e.getMessage() + "】");
		} catch (Exception e) {
			logger.warn("【Exception: " + e.getMessage() + "】");
		}
		return retMap;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static JSONObject cteateMessage(String str){
		JSONObject json = null;
		try {
			json = new JSONObject(str);
		} catch (JSONException e) {
			logger.warn("JSONException:"+e.getMessage(),e);
		}
		logger.info("JSON:"+json);
		return json;
	}
	
	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		String str = "{\"message\":\"connected successful!\",\"msgtype\":\"ping\"}";
		JSONObject json = new JSONObject(str);
		System.out.println(json);
		System.out.println(json2map(json));
	}
}
