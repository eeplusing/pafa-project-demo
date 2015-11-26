package com.ring.front.biz.util.enums;

public enum BuyStateEnums {
	readying("0", "筹备中"), 
	onsale("1", "购买"), 
	saledover("2", "认购满额"), 
	refunded("4", "已完结");

	private String code;// 返回状态码
	private String message;// 返回状态信息

	BuyStateEnums(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
