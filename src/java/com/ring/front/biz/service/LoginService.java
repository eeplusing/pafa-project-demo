package com.ring.front.biz.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.front.biz.dao.RegisterDao;
import com.ring.front.dto.LoginInfoDTO;

/**
 * @desc 会员登陆service
 * @author ganchungen
 * @since 2014-10-08
 */
@Service("loginService")
public class LoginService{
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private RegisterDao registerDao;

	/**
	 * @desc 会员首次登陆接口
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean validatePassword_1(LoginInfoDTO dto) {
		return registerDao.login(dto);
	}

	/**
	 * @desc 会员非首次登陆接口
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean validatePassword_2(LoginInfoDTO dto) {
		return registerDao.login_non_first(dto);
	}
	
}
