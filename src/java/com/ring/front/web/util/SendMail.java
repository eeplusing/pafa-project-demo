package com.ring.front.web.util;
import com.ykmaiz.mail.MailSupport;


public class SendMail {
	
	
	public static void main(String[] args) {
		/**
		 *  QQ邮箱发送实例
		 *	邮箱	    POP3服务器（端口995） SMTP服务器（端口465或587）
		 *	qq.com	pop.qq.com	         smtp.qq.com
		 */
		/* MailSupport mailSupport = new MailSupport("smtp.qq.com", "460772869",
				"520heLu1314+", false);

		mailSupport.send("460772869@qq.com", "751259253@qq.com", "您好！",
				"您好！QQ Blog~~~");*/
		
		/**
		 *  网易邮箱发送实例
		 *	邮箱	POP3       服务器（端口110）	 SMTP 服务器（端口25）
		 *	188.com     pop3.188.com	     smtp.188.com
		 *  163.com     pop3.163.com	     smtp.163.com
		 *  126.com     pop3.126.com	     smtp.126.com
		 *	netease.com pop.netease.com	     smtp.netease.com
		 *	yeah.net    pop.yeah.net	     smtp.yeah.net
		 */
//		MailSupport mailSupport = new MailSupport("smtp.163.com", "luohuan_java",
//				"520heLu1314+", false);
//
//		mailSupport.send("luohuan_java@163.com", "13428920575@126.com", "您好！",
//				"您好！126 Blog~~~");
		
		MailSupport mailSupport = new MailSupport("smtp.qq.com", "617934354",
				"ganchungen1986", false);

		mailSupport.send("617934354@qq.com", "13428920575@126.com", "您好！",
				"您好！126 Blog~~~");
	}
}
