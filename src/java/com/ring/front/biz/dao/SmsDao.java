package com.ring.front.biz.dao;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.paic.pafa.biz.dao.BaseDAO;
import com.ring.front.biz.util.IdUtils;

/**
 * @desc 短信DAO
 * @author ganchungen
 * @since 2015-05-26
 */
@Service("smsDao")
public class SmsDao extends BaseDAO{

	/**
	 * @desc 保存短信验证码
	 * @param String mobile
	 * @param String msgCode
	 * @return void
	 */
	public void saveMessage(String mobile, String msgCode){
		logger.info("进入SmsDao的saveMessage()方法。。。。。。");
		HashMap<String,String> condition = new HashMap<String,String>();
		condition.put("id", IdUtils.getUUID());
		condition.put("mobile", mobile);
		condition.put("msgCode", msgCode);
		condition.put("state", "0");
		logger.info("condition=="+condition);
		this._insert("save-message-code", condition);
	}
	
	/**
	 * @desc 根据手机号+短信验证码查询
	 * @param String mobile
	 * @param String msgCode
	 * @return int count
	 */
	public boolean validateMsgCode(String mobile, String msgCode){
		logger.info("进入SmsDao的validateMsgCode()方法。。。。。。");
		HashMap<String,String> condition = new HashMap<String,String>();
		condition.put("mobile", mobile);
		condition.put("msgCode", msgCode);
		condition.put("state", "1");
		logger.info("condition=="+condition);
		int count =  this._update("validate-message-code", condition);
		logger.info("count==="+count);
		return count>0 ? true : false;
	}
}
