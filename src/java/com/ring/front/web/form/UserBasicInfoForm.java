package com.ring.front.web.form;

import com.paic.pafa.web.form.BaseForm;

public class UserBasicInfoForm  extends BaseForm{

	/**
     * 用户名
     */
    private String username;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 住址
     */
    private String address;
    /**
     * 座机
     */
    private String phone;
    /**
     * 生日
     */
    private String birthday;
    
    /**
     * 邮箱
     */
    private String email;
    /**
     * 证件号
     */
    private String IDnum;
    /**
     * 学历 
     */
    private String education;
    
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIDnum() {
		return IDnum;
	}
	public void setIDnum(String iDnum) {
		IDnum = iDnum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
