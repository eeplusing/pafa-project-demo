package com.ring.front.util;

import it.sauronsoftware.base64.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @desc 签名工具类
 * @author ganchungen
 * @since 2014-10-04
 */
public class SignUtils {
	protected static Log logger = LogFactory.getLog(SignUtils.class);
	
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "utf-8";
	
	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * @param encryptText 被签名的字符串
	 * @param encryptKey 密钥
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception{
		byte[] data = null;
		byte[] text = null;
		Mac mac = null;
		try {
			data = encryptKey.getBytes(ENCODING);
			// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
			SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
			mac = Mac.getInstance(MAC_NAME);// 生成一个指定 Mac 算法 的 Mac 对象
			mac.init(secretKey);// 用给定密钥初始化 Mac 对象
			text = encryptText.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error("SHA1加密异常：", e);
			throw new Exception("编码异常:"+e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.error("获取Mac对象异常：", e);
			throw new Exception("SHA1加密异常:"+e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error("加密key无效异常：", e);
			throw new Exception("SHA1加密异常:"+e.getMessage());
		} 
		return mac.doFinal(text);// 完成 Mac 操作
	}
	
	/**
	 * 使用Base64 签名方法对对signParamsByte进行签名
	 * @param signParamsByte 被签名的字节数组
	 * @return String
	 * @throws Exception
	 */
	public static String Base64Sign(byte[] signParamsByte) throws Exception{
		String signStr = "";
		try {
			signStr = new String(Base64.encode(signParamsByte), ENCODING);
			signStr = URLEncoder.encode(signStr, ENCODING);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signStr;
	}
}
