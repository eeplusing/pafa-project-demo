package com.ring.front.web.rest;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paic.pafa.web.BaseRest;
import com.ring.front.biz.service.LoginService;
import com.ring.front.biz.service.UserInfoService;
import com.ring.front.dto.LoginInfoDTO;
import com.ring.front.dto.UserInfoDTO;
import com.ring.front.web.constants.WebConstants;
import com.ring.front.web.util.StringUtils;
import com.ring.front.web.util.WebUtils;

/**
 * @DESC 登陆Rest
 * @author Bill
 * @createtime: 2015年3月8日
 */
@Controller
@RequestMapping(value = "/ring")
public class LoginRestController extends BaseRest {
	protected Log logger = LogFactory.getLog(LoginRestController.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/login")
	public HashMap<String,Object> login(@RequestParam(value = "mobile", required = false) String mobile,
										@RequestParam(value = "password", required = false) String password,
										@RequestParam(value = "deviceId", required = false) String deviceId,
										@RequestParam(value = "token", required = false) String token) {
		logger.info("进入LoginRestController的login方法............");
		logger.info("email="+mobile+" || password="+password+" || deviceId="+deviceId+" || token="+token);
		LoginInfoDTO loginInfo = new LoginInfoDTO();
		loginInfo.setMobile(mobile);
		loginInfo.setPassword(password);
		loginInfo.setDeviceID(deviceId);
		loginInfo.setToken(token);
		HashMap<String,Object> json = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(password) && loginService.validatePassword_1(loginInfo)){
			logger.info("首次登陆，用户信息验证成功........");
//			UserInfosInSessionDTO sessionDto = new UserInfosInSessionDTO();
//			sessionDto.seteMail(email);
//			WebUtils.saveUserInfo(sessionDto); // 维护用户信息进session
			UserInfoDTO userInfo = userInfoService.queryUserInfoByMobile(mobile);
			json.put("userInfo",  userInfo);
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "登陆成功");
			return json;
		}
		
		if(loginService.validatePassword_2(loginInfo)){
			logger.info("持续登陆，用户信息验证成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "持续登陆成功");
			return json;
		}
		logger.warn("登陆失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "登陆失败");
		return json;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/login-out")
	public HashMap<String,String> loginOut() {
		WebUtils.loginOut();
		HashMap<String,String> json = new HashMap<String,String>();
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  WebConstants.RETURN_SUCCESS_MSG);
		return json;
	}
}
