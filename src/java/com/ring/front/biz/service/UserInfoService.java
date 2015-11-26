package com.ring.front.biz.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.front.biz.dao.RegisterDao;
import com.ring.front.dto.RegisterInfosDTO;
import com.ring.front.dto.UserInfoDTO;

/**
 * @desc 用户信息service
 * @author ganchungen
 * @since 2014-09-26
 */
@Service("userInfoService")
public class UserInfoService{
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private RegisterDao registerDao;
	/**
	 * @desc会员注册接口
	 * @param RegisterInfosDTO
	 * @return boolean
	 */
	public boolean regist(RegisterInfosDTO dto) {
		logger.info("进入UserInfoService的regist()方法........");
		if(registerDao.regist(dto)){
			logger.info("注册成功......");
			return true;
		}
		
		logger.warn("注册失败......");
		return false;
	}

	/**
	 * @desc 判断手机号是否被注册
	 * @param mobile 用户名
	 * @return 手机号已被注册,返回true;否则,返回false
	 */  
	public boolean mobileIsRegisted(String mobile) {
		logger.info("判断【"+mobile+"】是否已经被注册。。。。。");
		UserInfoDTO userInfo = queryUserInfoByMobile(mobile);
		if(null==userInfo){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @desc 根据手机号查询用户信息
	 * @param String mobile
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByMobile(String mobile){
		logger.info("进入UserInfoService的queryUserInfoByMobile()方法。。。。。。");
		logger.info("mobile==="+mobile);
		return registerDao.queryUserInfoByMobile(mobile);
	}
	
	/**
	 * @desc 根据用户ID查询用户信息
	 * @param String id
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoById(String id){
		logger.info("进入UserInfoService的queryUserInfoById()方法。。。。。。");
		logger.info("id==="+id);
		return registerDao.queryUserInfoById(id);
	}
	
	/**
	 * @desc 编辑用户信息
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public boolean editUserInfo(UserInfoDTO userInfo){
		logger.info("进入UserInfoService的editUserInfo()方法。。。。。。");
		return registerDao.editUserInfo(userInfo);
	}
}