package com.ring.front.dto;

import java.io.Serializable;

/**
 * @desc 用户信息DTO
 * @author ganchungen
 * @since 2014-10-02
 */
public class UserInfoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 2703915949327105353L;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 性别：M--男，F--女
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 身高
	 */
	private String high;
	/**
	 * 体重
	 */
	private String weight;
	/**
	 * 步长
	 */
	private String stepLong;
	/**
	 * 头像
	 */
	private byte[] headImage;
	/**
	 * 设备号
	 */
	private String deviceId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 外部关联的设备号
	 */
	private String ex_deviceId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public byte[] getHeadImage() {
		return headImage;
	}
	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEx_deviceId() {
		return ex_deviceId;
	}
	public void setEx_deviceId(String ex_deviceId) {
		this.ex_deviceId = ex_deviceId;
	}
	public String getStepLong() {
		return stepLong;
	}
	public void setStepLong(String stepLong) {
		this.stepLong = stepLong;
	}
}
