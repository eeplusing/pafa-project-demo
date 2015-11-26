package com.ring.front.web.rest;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paic.pafa.web.BaseRest;
import com.ring.front.biz.service.SmsService;
import com.ring.front.web.constants.WebConstants;

/**
 * @DESC 短信Rest
 * @author Bill
 * @createtime: 2015年3月8日
 */
@Controller
@RequestMapping(value = "/ring")
public class SmsRestController extends BaseRest {
	protected Log logger = LogFactory.getLog(SmsRestController.class);
	@Autowired
	private SmsService smsService;
	
	/**
	 * @发送短信验证码
	 * @param mobile
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/send-msg")
	public HashMap<String,String> sendMessage(@RequestParam(value = "mobile", required = false) String mobile) {
		logger.info("进入SmsRestController的sendMessage方法............");
		logger.info("mobile="+mobile);
		HashMap<String,String> json = new HashMap<String,String>();
		if(smsService.sendMessage(mobile)){
			logger.info("短信发送成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "短信发送成功");
			return json;
		}
		
		logger.warn("短信发送失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "短信发送失败");
		return json;
	}
	
	/**
	 * @验证短信验证码
	 * @param mobile
	 * @param msgCode
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/validate-msg")
	public HashMap<String,String> validateMessage(@RequestParam(value = "mobile", required = true) String mobile,
											      @RequestParam(value = "msgCode", required = true) String msgCode) {
		logger.info("进入SmsRestController的validateMessage方法............");
		logger.info("mobile="+mobile+"  ||　　msgCode+=="+msgCode);
		HashMap<String,String> json = new HashMap<String,String>();
		if(smsService.validateMsgCode(mobile, msgCode)){
			logger.info("短信验证成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "短信验证成功");
			return json;
		}
		
		logger.warn("短信验证失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "短信验证失败");
		return json;
	}
}
