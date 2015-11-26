package com.ring.front.biz.dao;

import org.springframework.stereotype.Service;

import com.paic.pafa.biz.dao.BaseDAO;
import com.ring.front.dto.LoginInfoDTO;
import com.ring.front.dto.RegisterInfosDTO;
import com.ring.front.dto.UserInfoDTO;

/**
 * @desc 会员注册DAO
 * @author ganchungen
 * @since 2014-09-26
 */
@Service("registerDao")
public class RegisterDao extends BaseDAO{

	/**
	 * @desc会员注册接口
	 * @param regMap
	 * @return boolean
	 */
	public boolean regist(RegisterInfosDTO dto){
		logger.info("进入RegisterDaoImpl的regist()方法...........");
		logger.info("RegisterInfosDTO==:"+dto);
		RegisterInfosDTO regInfo = this._insert("ring-regist", dto);
		logger.info("regInfo---->"+regInfo);
		return true;
	}
	
	/**
	 * @desc 登陆
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean login(LoginInfoDTO dto){
		logger.info("进入RegisterDaoImpl的login()方法...........");
		logger.info("LoginInfoDTO==:"+dto);
		int count = this._getInt("ring-login", dto);
		logger.info("count---->"+count);
		return count>0 ? true : false;
	}

	/**
	 * @desc 非首次登陆
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean login_non_first(LoginInfoDTO dto){
		logger.info("进入RegisterDaoImpl的login()方法...........");
		logger.info("LoginInfoDTO==:"+dto);
		int count = this._getInt("ring-login-non-first", dto);
		logger.info("count---->"+count);
		return count>0 ? true : false;
	}
	
	/**
	 * @desc 根据手机号查询用户信息
	 * @param String mobile
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByMobile(String mobile){
		logger.info("进入RegisterDao的queryUserInfoByMobile()方法。。。。。。mobile=="+mobile);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-mobile", mobile);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 根据用户ID查询用户信息
	 * @param String id
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoById(String id){
		logger.info("进入RegisterDao的queryUserInfoByMobile()方法。。。。。。id=="+id);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-id", id);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 编辑用户信息
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public boolean editUserInfo(UserInfoDTO userInfo){
		logger.info("进入RegisterDao的editUserInfo()方法。。。。。。");
		int count = this._update("edit-user-info", userInfo);
		return count>0 ? true : false;
	}
}
