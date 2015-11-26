package com.ring.front.biz.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.front.biz.dao.SmsDao;
import com.ring.front.util.ValidateCodeUtils;

/**
 * @desc 短信service
 * @author ganchungen
 * @since 2015-05-26
 */
@Service("smsService")
public class SmsService{
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SmsDao smsDao;

	/**
	 * @desc 发送短信
	 * @param String mobile
	 * @return boolean
	 */
	public boolean sendMessage(String mobile) {
		logger.info("进入SmsService的validateMsgCode()方法........");
		String msgCode = ValidateCodeUtils.getValidateCode(ValidateCodeUtils.NUMBER_TYPE, 4, 8);
		boolean sendResult = true;//这里发送短信验证码
		if(sendResult){
			smsDao.saveMessage(mobile, msgCode);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @desc 根据手机号+短信验证码查询
	 * @param String mobile
	 * @param String msgCode
	 * @return int count
	 */
	public boolean validateMsgCode(String mobile, String msgCode) {
		logger.info("进入SmsService的validateMsgCode()方法........");
		return smsDao.validateMsgCode(mobile, msgCode);
	}
}