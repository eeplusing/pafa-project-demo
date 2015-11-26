package com.ring.front.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ring.front.web.constants.WebConstants;

public class StringUtils {

	/**
	 * 字符串是否为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(String... params) {

		if (params == null || params.length == 0) {
			return true;
		}

		String[] strs = params.clone();
		for (String str : strs) {
		    if (str == null || str.trim().equals("")) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isNotEmpty(String... params) {
		if (params == null || params.length == 0) {
			return false;
		}

		String[] strs = params.clone();
		for (String str : strs) {
		    if (str == null || str.trim().equals("")) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 心号格式校验，以字母开头，由字母或数字组成，且长度为6-20个字符
	 * 
	 * @param heartId
	 * @return
	 */
	public static boolean isValidHeartID(String heartId) {
		if (isEmpty(heartId)) {
			return false;
		}
		boolean isValid = false;
		if (heartId.matches("^[A-Za-z]+[A-Za-z0-9]+$")
				&& heartId.length() >= 6 && heartId.length() <= 20) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 手机号格式校验，纯数字，长度11位，国内手机
	 * 
	 * @param mobilephone
	 * @return
	 */
	public static boolean isValidMobilephone(String mobilephone) {
		if (isEmpty(mobilephone)) {
			return false;
		}
		boolean isValid = false;
		if (mobilephone.matches("^[0-9]+$") && mobilephone.length() == 11) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 昵称格式校验
	 * 
	 * @param nickname
	 * @return
	 */
	public static boolean isValidNickname(String nickname) {
		if (isEmpty(nickname)) {
			return false;
		}
		boolean isValid = false;
		if (nickname.length() <= 20) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 将以&&连接的字符串，转换成list
	 * @param s
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 public static List fromString2List(String s){
			List list  = new ArrayList();
			if(null!=s){
				String [] array = s.split(WebConstants.SPLIT_CHAR);
				 Collections.addAll(list,array);
			}
			return list;
		}
}
