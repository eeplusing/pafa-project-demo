package com.ring.front.dto;

import java.io.Serializable;

/**
 * 邮件信息类 
 * @author zhangguohua
 *
 */
public class MailInfoDTO extends BasicDTO implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发送邮件的host地址
	 */
	private String host ;
	/**
	 * 发件人邮箱地址
	 */
	private String formName ;
	/**
	 * 发件人名称
	 */
	private String userName;
	/**
	 * 发送邮件的pwd
	 */
	private String formPassword ;
	/**
	 * 回复邮件地址
	 */
	private String replayAddress ;
	/**
	 * 发送目的地 邮件地址
	 */
	private String toAddress ;
	/**
	 * 邮件标题
	 */
	private String subject ;
	/**
	 * 邮件内容
	 */
	private String content ;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormPassword() {
		return formPassword;
	}
	public void setFormPassword(String formPassword) {
		this.formPassword = formPassword;
	}
	public String getReplayAddress() {
		return replayAddress;
	}
	public void setReplayAddress(String replayAddress) {
		this.replayAddress = replayAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
