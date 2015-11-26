package com.ring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.caucho.hessian.client.HessianProxyFactory;
import com.paic.pafa.app.biz.ac.ApplicationController;
import com.paic.pafa.appclient.BaseApplicationControllerClient;

public class ApplicationControllerHessianClient extends
		BaseApplicationControllerClient implements InitializingBean {

	/** Ŀ��������:http://localhost:7001 */
	private String serviceUrl;
	//

	private ApplicationController proxy;
	private Object _lock = new Object();

	private int readTimeout = 30 * 1000;

	public void setProxy(ApplicationController proxy) {
		this.proxy = proxy;
	}

	@Override
	public ApplicationController getProxy() {
		return proxy;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (proxy == null) {
			// ------------------------------
			synchronized (_lock) {
				HessianProxyFactoryBean proxyFactory = new HessianProxyFactoryBean();
				HessianProxyFactory temp = new HessianProxyFactory();
				temp.setReadTimeout(readTimeout);
				proxyFactory.setProxyFactory(temp);
				//
				// ------------------------------------
				proxyFactory.setServiceInterface(ApplicationController.class);
				proxyFactory.setServiceUrl(serviceUrl + "/hessian.ac");
				proxyFactory.afterPropertiesSet();
				this.proxy = (ApplicationController) proxyFactory.getObject();
			}
		}
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

}
