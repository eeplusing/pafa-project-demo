package com.ring.front.web.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ring.front.dto.UserInfosInSessionDTO;
import com.ring.front.util.PropertiesConfigUtil;


/**
 * Web工具类
 * @author luohuan
 * @date 2014-10-23
 */
public class WebUtils {
	protected static Log logger = LogFactory.getLog(WebUtils.class);
	
	private final static String USER_INFO = "user";
	
	// Either Hibernate3 or Spring3
	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		}catch(NullPointerException e) {
			session = null;
		}
		//logger.info("session == " + session);
		return session;
	}
	
	// 保存用户信息(包含userId、mobile、userName、userType等重要字段)
	public static void saveUserInfo(UserInfosInSessionDTO userInfo) {
//		logger.info("保存用户登录信息: " + userInfo.toString());
		getSession().setAttribute(USER_INFO, userInfo);
	}
	
	// 获取用户信息
	public static UserInfosInSessionDTO getUserInfo() {
		if (getSession() == null) {
			return null;
		}
		Object userInfo = getSession().getAttribute(USER_INFO);
		if (null != userInfo) {
//			logger.info("当前用户对象: " + userInfo.toString());
			return (UserInfosInSessionDTO) userInfo;
		} else {
			//logger.info("当前用户对象为空!");
			return null;
		}
	}
	
	// 注销
	public static void loginOut() {
		logger.info("注销当前用户!");
		getSession().setAttribute(USER_INFO, null);
	}
	
	// 获取当前登录用户
	public static String getCurrentUser() {
		UserInfosInSessionDTO userInfo = getUserInfo();
		if (null != userInfo) {
//			logger.info("当前登录的用户名为: " + userInfo.getUserName());
			return userInfo.getNickName();
		} else {
			//logger.info("用户未登录!");
			return null;
		}
	}
	
	
	// 获取url
	public static String getUrl() {
		String url = PropertiesConfigUtil.getProperty("lighting.address.url");
//		logger.info("url-------->"+url);
		return url;
	}
}
