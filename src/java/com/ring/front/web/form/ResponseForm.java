package com.ring.front.web.form;

import com.paic.pafa.web.form.BaseForm;

/**
 * 请求返回的ResponseForm，包括请求状态码,返回消息,返回数据
 * */
public class ResponseForm extends BaseForm {

	private String code; /* 请求状态码 */
	private String message;/* 返回消息 */

	private long totalCount;
	private Integer pageNumber;

	public static ResponseForm getForm(String code, String message) {
		ResponseForm form = new ResponseForm();
		form.code = code;
		form.message = message;
		return form;
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

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
