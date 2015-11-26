package com.ring.front.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ring.front.dto.MailInfoDTO;
import com.ring.front.web.constants.WebConstants;
/**
 * 邮件发送工具类
 * @author zhangguohua
 * 2014-11-21
 */

public class MailSendUtils {
	protected static Log logger = LogFactory.getLog(MailSendUtils.class);
	private final static String COMPANY_EMAIL_ADDRESS = PropertiesConfigUtil.getProperty("company.email.address");//公司邮件地址
	private final static String USER = WebConstants.APP_NAME;// 发件人
	private final static String PASSWORD = PropertiesConfigUtil.getProperty("company.email.password");//邮件密码
	private final static String FROM_HOST = PropertiesConfigUtil.getProperty("company.email.from.host");//smtp host address

	
	public static void sendHtmlMail(MailInfoDTO info)throws Exception{
		Message message = getMessage(info);
		message.setContent(info.getContent(), "text/html;charset=utf-8");
		Transport.send(message);
	}
	
	public static void sendTextMail(MailInfoDTO info)throws Exception{
		info.setHost(FROM_HOST);// 发送邮箱的host
		info.setFormName(COMPANY_EMAIL_ADDRESS);// 发件人地址
		info.setFormPassword(PASSWORD);// 发件人邮箱密码
		info.setReplayAddress(FROM_HOST);// 发件人邮箱
		info.setUserName(USER);// 发件人
		logger.info("发送的邮件信息MailInfoDTO=="+info);
		Message message = getMessage(info);
		message.setText(info.getContent());
		Transport.send(message);
	}
	
	private static Message getMessage(MailInfoDTO info) throws Exception{
		final Properties  p = System.getProperties() ;
		p.setProperty("mail.smtp.host", info.getHost());
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.user", info.getFormName());
		p.setProperty("mail.smtp.pass", info.getFormPassword());
		
		Session session = Session.getInstance(p, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
			}
		});
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setSubject(info.getSubject());
		message.setReplyTo(InternetAddress.parse(info.getReplayAddress()));
		message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),info.getUserName()));
		message.setRecipient(RecipientType.TO,new InternetAddress(info.getToAddress()));
		return message ;
	}
	
	public static void main(String[] args) throws Exception{
		MailInfoDTO info = new MailInfoDTO();
		info.setHost("smtp.exmail.qq.com");
		info.setFormName("hm_service@hangmeicn.com");
		info.setFormPassword("hmjj321123");
		info.setReplayAddress("187247602@sina.com");
		info.setToAddress("187247602@qq.com");
		info.setSubject("bbs测试邮件");
		info.setContent("这是一封测试邮件");
		sendTextMail(info);
	}
}
