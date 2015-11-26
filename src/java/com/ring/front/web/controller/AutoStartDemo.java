package com.ring.front.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 
 * @author GANCHUNGEN512
 *
 */

@Service("autoStartDemo")
public class AutoStartDemo {
//	implements InitializingBean, BeanFactoryAware {
	protected Log logger = LogFactory.getLog(this.getClass());
	
//	@Override
//	public void setBeanFactory(BeanFactory arg0) throws BeansException {
//		System.out.println("+++++++++++++++++++++++++++++++++++");
//	}

	
//	public void afterPropertiesSet() throws Exception {
//		System.out.println("==============================come in......");
//		for(int i=0; i<10; i++){
//			initParameter(""+i);
//		}
//	}

	/**
	 * 鍒濆鍖栧弬鏁�
	 */
//	private void initParameter(String s) {
//		System.out.println(s);
//	}
}
