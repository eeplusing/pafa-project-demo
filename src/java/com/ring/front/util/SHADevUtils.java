package com.ring.front.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SHADevUtils {
	
	private static final String KEY_SHA = "SHA";
    private static final String KEY_MD5 = "MD5";
    private static final String CODE_TYPE = "ISO8859-1";  
	
    /** 
     * SHA
     *  
     * @param sourceStr 
     * @return 原始加密串
     * @throws Exception 
     */  
    public static byte[] encryptSHA(String sourceStr) throws Exception {
    	byte[] data = sourceStr.getBytes(CODE_TYPE);
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
        sha.update(data);  

        return sha.digest();  
    }
    
    /**
     * MD5加密
     * 
     * @param sourceStr
     * @return 原始加密串
     * @throws Exception
     */
    public static byte[] encryptMD5(String sourceStr) throws Exception {
    	byte[] data = sourceStr.getBytes(CODE_TYPE);
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
 
        return md5.digest();
 
    }
    
    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
    
    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    
	private final static String NUM_ARR = "0123456789";
	private final static String EN_ARR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
	// 字符转换
    public static String changeChar(String sourceStr) {
    	StringBuffer sb = new StringBuffer("");
    	for (int i = 0;i <sourceStr.length();i++) {
    		char temp = '\u0000';
    		String item = sourceStr.substring(i, i+1);
    		// 取散列值
    		// temp = (char)(item.hashCode()<<2); 
    		if (NUM_ARR.indexOf(item) != -1) {
    			// 数字. 对位交换
    			int changePos = Math.abs(NUM_ARR.length() - 1 - NUM_ARR.indexOf(item));
    			temp = NUM_ARR.charAt(changePos);
    			sb.append(temp);
    		} else if(EN_ARR.indexOf(item) != -1){
    			// 英文. 前后位置交换
    			int changePos = EN_ARR.indexOf(item)+1;
    			changePos = changePos>EN_ARR.length()-1?0:changePos;
    			temp = EN_ARR.charAt(changePos);
    			sb.append(temp);
    		} else {
    			// 符号.
    			sb.append(item);
    		}
    	}
    	return sb.toString();
    }
    
    
	/**
	 * 获取密码
	 * @param sourceStr 原始密码串
	 * @return 加密后的字串
	 */
	public static String encryptPwd(String password) throws Exception {
		return new BigInteger(encryptSHA(changeChar(password))).toString();
	}
    
    public static void main(String[] args) throws Exception {

    	/*String a = "1233fA+";
    	String b = "1233fA+";
    	String c = "111111";
    	
    	// Base64测试
    	System.out.println("BASE64加密: " + encryptBASE64(a.getBytes()));
    	System.out.println("BASE64解密: " + new String(decryptBASE64("MTIzM2ZBKw==")));
    	
    	// MD5测试
    	// -2140e069518b2b32f77ec4bcb3fa5598
    	BigInteger md5 = new BigInteger(encryptMD5(a));
    	System.out.println("MD5加密: a== " + md5.toString(16));
    	BigInteger md6 = new BigInteger(encryptMD5(b));
    	System.out.println("MD5加密: b== " + md6.toString(16));
    	BigInteger md7 = new BigInteger(encryptMD5(c));
    	System.out.println("MD5加密: c== " + md7.toString(16));
    	
    	// SHA测试
    	// 9c5alemge9tcube5e0ds08v8u004mk74
    	BigInteger sh1 = new BigInteger(encryptSHA(a));
    	System.out.println("SHA加密: a== " + sh1.toString(32));
    	BigInteger sh2 = new BigInteger(encryptSHA(b));
    	System.out.println("SHA加密: b== " + sh2.toString(32));
    	BigInteger sh3 = new BigInteger(encryptSHA(c));
    	System.out.println("SHA加密: c== " + sh3.toString(32));
    
    	// 组合加密 -414e0b861fbc34350a2128ec34c75ac8
    	// BASE64 + MD5
    	BigInteger zuhe1 = new BigInteger(encryptMD5("MTIzM2ZBKw=="));
    	System.out.println("组合加密1: a== " + zuhe1.toString(16));
    	
    	// 组合加密2 -ctecr4rs51m4hgbeas7dom9rlflu6s8j
    	// 字符变换 + SHA 
    	BigInteger zuhe2 = new BigInteger(encryptSHA(changeChar(c)));
    	System.out.println("组合加密2: a== " + zuhe2.toString(32));*/
    	
    	System.out.println(encryptPwd("111111"));
    }
}
