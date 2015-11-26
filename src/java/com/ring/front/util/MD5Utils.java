package com.ring.front.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @desc MD5加密工具类
 * @author ganchungen
 * @since 2014-10-02
 */
public class MD5Utils {

	protected static Log logger = LogFactory.getLog(MD5Utils.class);
	/**
	 * @desc 做MD5加密，生成一个32位长的hash值
	 * @param String
	 * @return String
	 */
	public static String makeMD5(String password) throws Exception{
		MessageDigest md = null;
	    try {
		    // 生成一个MD5加密计算摘要
		    md = MessageDigest.getInstance("MD5");
		    // 计算md5函数
		    md.update(password.getBytes());
		    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		    String pwd = new BigInteger(1, md.digest()).toString(16);
//			System.out.println(pwd);
		    return pwd;
	    } catch (Exception e) {
		   logger.error("失败原因:",e);
		   throw new Exception("MD5加密失败,失败原因:"+e.getMessage());
	    }
	}
	
	public static void main(String argsp[]) throws Exception{
		System.out.println("1------>"+makeMD5("111111"));
		System.out.println("2------>"+makeMD5("11111111111111"));
	}
}
