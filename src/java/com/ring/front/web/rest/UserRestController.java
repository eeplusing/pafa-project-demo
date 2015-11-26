package com.ring.front.web.rest;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paic.pafa.web.BaseRest;
import com.ring.front.biz.service.UserInfoService;
import com.ring.front.dto.UserInfoDTO;
import com.ring.front.web.constants.WebConstants;

/**
 * @DESC 用户Rest
 * @author Bill
 * @createtime: 2015年3月8日
 */
@Controller
@RequestMapping(value = "/ring")
public class UserRestController extends BaseRest {
	protected Log logger = LogFactory.getLog(UserRestController.class);
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/eidt-user-info")
	public HashMap<String,String> editUserInfo(@RequestParam(value = "id", required = true) String id,
										@RequestParam(value = "password", required = false) String password,
										@RequestParam(value = "sex", required = false) String sex,
										@RequestParam(value = "high", required = false) String high,
										@RequestParam(value = "weight", required = false) String weight,
										@RequestParam(value = "age", required = false) String age,
										@RequestParam(value = "stepLong", required = false) String stepLong) {
		logger.info("进入UserRestController的editUserInfo方法............");
		UserInfoDTO userInfo = new UserInfoDTO();
		userInfo.setId(id);
		userInfo.setPassword(password);
		userInfo.setSex(sex);
		userInfo.setHigh(high);
		userInfo.setWeight(weight);
		userInfo.setAge(age);
		userInfo.setStepLong(stepLong);
		logger.info("userInfo=="+userInfo);
		HashMap<String,String> json = new HashMap<String,String>();
		if(userInfoService.editUserInfo(userInfo)){
			logger.info("用户信息编辑成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "用户信息编辑成功........");
			return json;
		}
		
		logger.warn("用户信息编辑失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "用户信息编辑失败........");
		return json;
	}
	
}
