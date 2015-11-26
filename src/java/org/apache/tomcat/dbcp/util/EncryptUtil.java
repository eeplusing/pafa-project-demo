package org.apache.tomcat.dbcp.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * <p> 概要信息:加密工具类 状态信息: 操作系统/硬件依赖/应用时可能存在的差异: ��安全约束: ��序列化格式: ��参考的外部规格说明: </p> <p> 版权: ��2008 中国保险（集团）股份有限公司 版权所有 </p>
 * 
 * @author liuxq
 * @version %I%, %G%
 * @history 2008-7-14
 */
public class EncryptUtil {
    public static final String Algorithm = "PBEWithMD5AndDES";
    public static final String Algorithm3 = "AES"; //DES 、AES
    // Salt
    public static final byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8,
            (byte) 0xee, (byte) 0x99 };

    // Iteration count
    public static final int count = 32;

    public static String encrypt(String source, String keyValue) throws Exception {
        return encrypt1(source, keyValue);
    }

    public static String encode(String source) {
        String result = source;
        BASE64Encoder encoder = new BASE64Encoder();
        result = encoder.encode(source.getBytes());
        return result;
    }

    /*
     * encrypt a password
     */
    public static String encrypt1(String pwd, String keyFile) throws Exception {

        SecretKeySpec skeySpec;
        Cipher cipher = Cipher.getInstance(Algorithm3);
        FileInputStream fis = new FileInputStream(keyFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        skeySpec = (SecretKeySpec) ois.readObject();
        ois.close();
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(pwd.getBytes());
        return StringUtil.asHex(encrypted);

    }

    /**
     * 获取Key对象保存到key文件中
     * @param fileName 保存key文件的文件名
     * @throws Exception
     * @see
     * @since %I%
     */
    public static void genKeyFile(String fileName) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(Algorithm3);
        keyGen.init(128);
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(fileName));
        //out.writeObject(genKey3("yht@system901122"));
        out.writeObject(keyGen.generateKey());
        out.close();

    }

    public static String encrypt2(String source, String keyValue) throws Exception {
        String result = source;
        Cipher c1 = Cipher.getInstance(Algorithm);
        PBEParameterSpec ap = new PBEParameterSpec(salt, count);
        c1.init(Cipher.ENCRYPT_MODE, genKey(keyValue), ap);
        byte[] cipherByte = c1.doFinal(result.getBytes());
        result = StringUtil.asHex(cipherByte);
        return result;
    }

    public static SecretKey genKey(String keyVal) throws Exception {
        SecretKey pbeKey = new SecretKeySpec(keyVal.getBytes(), Algorithm);
        return pbeKey;
    }

    public static String encrypt3(String source, String keyValue) throws Exception {
        String result = source;
        Cipher c1 = Cipher.getInstance(Algorithm3);
        c1.init(Cipher.ENCRYPT_MODE, genKey3(keyValue));
        byte[] cipherByte = c1.doFinal(result.getBytes());
        result = StringUtil.asHex(cipherByte);
        return result;
    }

    public static SecretKey genKey3(String keyValue) throws Exception {
        SecretKey skey = new javax.crypto.spec.SecretKeySpec(keyValue.getBytes(), Algorithm3);
        return skey;
    }

    public static void main(String[] args) throws Exception {
    	System.out.println("输入key keyfile来创建一个key文件;输入 enc str keyfile来加密一个字符串；");
    	if(args.length>2){
    		if("enc".equals(args[0])){
    			System.out.println("加密结果："+encrypt(args[1],args[2]));
    		}
    	}else if(args.length>1){
    		if("key".equals(args[0])){
    			genKeyFile(args[1]);
    			System.out.println("创建key文件成功！！文件名："+args[1]);
    		}
    	}
    	
//        String key = "yht@system901122";
//        String key3= "abcdefgh20080808";//必须是16位的字符
//        //System.out.println(System.currentTimeMillis());
//        String a = "testuser";
//        System.out.println(a);

        //System.out.println(System.currentTimeMillis());
        //a="1231761929571|b7acf3275ce03770d561492460b16009";
//        String ea = encrypt3(a, key);
//        System.out.println("加密："+ea);
//        System.out.println("解密："+DecryptUtil.decrypt3(ea, key));
//        String keyfile="d:/users/liuxq/key.store";
//        //genKeyFile(keyfile);
//		String ea = encrypt(a, keyfile);
//        System.out.println("加密："+ea);//0a3a31883036a82cb07f9b22132854a9
//        System.out.println("解密："+DecryptUtil.decrypt(ea, keyfile));
        //System.out.println(System.currentTimeMillis());

        //System.out.println(System.currentTimeMillis());
    }
}
