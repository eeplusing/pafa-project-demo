package com.ring.front.dto;

import java.io.Serializable;

/**
 * @登陆信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class LoginInfoDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 4216595863898981059L;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 手机验证码
	 */
	private String msgCode;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * token
	 */
	private String token;
	/**
	 * 设备ID
	 */
	private String deviceID;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
}
