package com.ring;

import java.lang.reflect.Method;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.Subject;

import org.springframework.jndi.JndiCallback;

import com.paic.pafa.app.lwc.core.naming.JndiTemplateUtil;
import com.paic.pafa.core.exceptions.PafaRuntimeException;
import com.paic.pafa.util.CyberArkUtil;

public class JndiTemplate extends org.springframework.jndi.JndiTemplate {

	

	private Subject subject = null;

	/**
	 * 域的描述：中间件产品的类型 合法值范围：weblogic或者jboss或者websphere.上述三者中间的任意一个.
	 * 空值说明：不能为空，默认为weblogic.
	 */
	private String middlewareType = JndiTemplateUtil.WEBLOGIC;

	
	private Properties environment;

	
	public JndiTemplate() {
	}

	
	public JndiTemplate(Properties environment) {
		this.environment = environment;
	}

	/**
	 * Set the environment for the JNDI InitialContext.
	 */
	@Override
	public void setEnvironment(Properties environment) {
		this.environment = environment;
	}

	/**
	 * Return the environment for the JNDI InitialContext.
	 */
	@Override
	public Properties getEnvironment() {
		if (this.environment != null) {
			if (this.environment.containsKey(CyberArkUtil.cyberArkCredFilePath)) {
				// 如果属性中包含cyberArk的必须字段，则调用CyberArkUtil对属性进行处理，获取密码
				CyberArkUtil.cyberArkHandler(this.environment);
			}
		}
		return this.environment;
	}

	/**
	 * Set the type of middleware
	 */
	public void setMiddlewareType(String middlewareType) {
		this.middlewareType = middlewareType;
	}

	/**
	 * Return the type of middleware
	 */
	public String getMiddlewareType() {
		return middlewareType;
	}

	/**
	 * 
	 * <p>
	 * 行为描述:获取缓存的security object.如果缓存为空,则抛出运行时异常. 状态转变: 定义的算法: 操作系统/硬件依赖:
	 * 允许的实现变化: 安全约束:
	 * </p>
	 */
	public Subject getSubject() throws NamingException {
		return this.subject;
	}

	
	@Override
	public Object lookup(final String name) throws NamingException {
		return execute(new JndiCallback<Object>() {
			@Override
			public Object doInContext(Context ctx) throws NamingException {
				logger.debug("Looking up JNDI object with name '" + name + "'");
				Object located = ctx.lookup(name);
				if (located == null) {
					throw new NamingException("JNDI object with '" + name
							+ "' not found: JNDI implementation returned null");
				}
				subject=getCurrentSubject();
				return located;
			}
		});
	}

	/**
	 * 
	 * <p>
	 * 行为描述:在当前的context环境下,获取security object.目前仅支持weblogic,其它中间件尚不支持 状态转变:
	 * 定义的算法: 操作系统/硬件依赖: 允许的实现变化: 安全约束:必须在当前的context(即context尚未关闭之前)下执行
	 * </p>
	 */
	private Subject getCurrentSubject() throws NamingException {
		if (middlewareType.equalsIgnoreCase(JndiTemplateUtil.WEBLOGIC)) {
			return getCurrentObjectFromWeblogic();
		} else {
			logger.warn("pafa framework supports weblogic only when caching security object currently! "
					+ middlewareType + " is not supported!");
			return null;
			//throw new NamingException(
			//		"pafa framework supports weblogic only when caching security object currently!");
		}
	}

	/**
	 * 
	 * <p>
	 * 行为描述:获取weblogic security object的方法.如果未能成功获取,则返回null. 状态转变: 定义的算法:
	 * 操作系统/硬件依赖: 允许的实现变化: 安全约束:
	 * </p>
	 */
	private Subject getCurrentObjectFromWeblogic() {
		try {
			logger.info("Get security object from weblogic beginning!");
			Class<?> claz = Class.forName("weblogic.security.Security");
			Method method = claz.getMethod("getCurrentSubject", (Class[]) null);
			Object obj = method.invoke(claz, (Object[]) null);
			logger.info("Get security object from weblogic successful!");
			return (Subject)obj;
		} catch (Exception e) {
			String msg="Can not getCurrentSubject.Detail error message is : "
				+ e.getMessage();
			logger.error(msg,e);
			throw new PafaRuntimeException(msg,e);
		}
	}

}
