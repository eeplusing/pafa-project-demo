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
import com.ring.front.biz.service.UserInfoService;
import com.ring.front.biz.util.IdUtils;
import com.ring.front.dto.RegisterInfosDTO;
import com.ring.front.web.constants.WebConstants;
import com.ring.front.web.util.StringUtils;

/**
 * @desc 注册 Controller
 * @author Bill
 * @createtime : 2015年5月25日
 */
@Controller
@RequestMapping(value = "/ring")
public class RegistRestController extends BaseRest {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SmsService smsService;
	
	@RequestMapping(value = "/regist")
	public HashMap<String,String> regist(@RequestParam(value = "mobile", required = false) String mobile,
										 @RequestParam(value = "msgCode", required = false) String msgCode,
										 @RequestParam(value = "nickName", required = false) String nickName,
										 @RequestParam(value = "password", required = false) String password,
										 @RequestParam(value = "confirmPassword", required = false) String confirmPassword
						  				) {
		logger.info("进入RegistRestController的regist方法............");
		HashMap<String,String> json = new HashMap<String,String>();
		String userId = IdUtils.getUUID();
		RegisterInfosDTO registInfo = new RegisterInfosDTO();
		registInfo.setUserId(userId);
		registInfo.setMobile(mobile);
		registInfo.setMsgCode(msgCode);
		registInfo.setPassword(password);
		registInfo.setConfirmPassword(confirmPassword);
		registInfo.setNickName(nickName);
		logger.info("RegisterInfosDTO======" + registInfo);
		if(StringUtils.isEmpty(mobile)){
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "手机号为空！");
			return json;
		}
		if(StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)){
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "密码或确认密码为空，注册失败！");
			return json;
		}
		
		//判断该手机号是否已被注册
		if(userInfoService.mobileIsRegisted(mobile)){
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "手机号【"+mobile+"】已被注册！");
			return json;
		}
		
		//判断手机验证码是否正确
		if(!smsService.validateMsgCode(mobile, msgCode)){
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "短信验证码错误！");
			return json;
		}
		
//		registInfo.setDeviceId(deviceId);
//		registInfo.setToken(token);
		if(userInfoService.regist(registInfo)){
//			WebUtils.saveUserInfo(sessionDto); // 维护用户信息进session
			logger.info("注册成功........");
			json.put("userid",  userId);
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg", "注册成功！");
		}else{
			logger.warn("注册失败........");
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "注册失败！");
		}
		return json;
	}
}
