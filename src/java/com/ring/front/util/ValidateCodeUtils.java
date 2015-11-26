package com.ring.front.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @desc 验证码工具类 
 * @author ganchungen
 * @since2014-10-31
 */
public class ValidateCodeUtils {
	protected static Log logger = LogFactory.getLog(ValidateCodeUtils.class);
	
	public static final String NUMBER_TYPE = "NUMBER";
	public static final String OTHER_TYPE = "OTHER";
	/**
	 * 使用Base64 签名方法对对signParamsByte进行签名
	 * @param signParamsByte 被签名的字节数组
	 * @return String
	 * @throws Exception
	 */
	public static String getValidateCode(String type, int start, int end){
		String str = "";
		String[] beforeShuffle = null;
		if(NUMBER_TYPE.equalsIgnoreCase(type)){
			beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5", "6", "7","8", "9"};
		}else{
			beforeShuffle = new String[] { "0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		}
		List<String> list = Arrays.asList(beforeShuffle);    
		Collections.shuffle(list);    
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {    
		   sb.append(list.get(i));    
		}
		String afterShuffle = sb.toString(); 
		logger.info("afterShuffle------->"+afterShuffle); 
		str = afterShuffle.substring(start, end);    
		logger.info("str------->"+str); 
		return str;
	}
}
