package org.apache.tomcat.dbcp.util;

import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.io.ClassPathResource;

import sun.misc.BASE64Decoder;


/**
 * <p> 概要信息:解密工具类 </p>
 * <p> 版权: ��2008 中国保险（集团）股份有限公司 版权所有 </p>
 * 
 * @author liuxq
 * @version %I%, %G%
 * @history 2008-7-14
 */
public class DecryptUtil {
    public static String decrypt(String source, String keyValue) throws Exception {
        return decrypt1(source, keyValue);
    }

    public static String decode(String source) throws Exception {
        String result = source;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] userCertBase64;
        userCertBase64 = decoder.decodeBuffer(source);
        result = new String(userCertBase64);
        return result;
    }

    /*
     * decrypt a password
     */
    public static String decrypt1(String pwd, String keyFile) throws Exception {

        SecretKeySpec skeySpec;
        Cipher cipher = Cipher.getInstance(EncryptUtil.Algorithm3);
       
        //FileInputStream fis = new FileInputStream(keyFile);
        //InputStream fis=ClassLoader.getSystemResourceAsStream(keyFile);
        
        ClassPathResource cpr=new ClassPathResource(keyFile);
        ObjectInputStream ois = new ObjectInputStream(cpr.getInputStream());
        skeySpec = (SecretKeySpec) ois.readObject();
        ois.close();
        byte[] encrypted = StringUtil.asByte(pwd);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);

    }

    public static String decrypt2(String source, String keyValue) throws Exception {
        String result = source;
        byte[] encrypted = StringUtil.asByte(result);
        Cipher c1 = Cipher.getInstance(EncryptUtil.Algorithm);
        PBEParameterSpec ap = new PBEParameterSpec(EncryptUtil.salt, EncryptUtil.count);
        c1.init(Cipher.DECRYPT_MODE, EncryptUtil.genKey(keyValue), ap);
        byte[] original = c1.doFinal(encrypted);
        result = new String(original);
        return result;
    }

    public static String decrypt3(String source, String keyValue) throws Exception {
        String result = source;
        Cipher c1 = Cipher.getInstance(EncryptUtil.Algorithm3);
        c1.init(Cipher.DECRYPT_MODE, EncryptUtil.genKey3(keyValue));
        byte[] clearByte = c1.doFinal(StringUtil.asByte(source));
        result = new String(clearByte);
        return result;
    }
}