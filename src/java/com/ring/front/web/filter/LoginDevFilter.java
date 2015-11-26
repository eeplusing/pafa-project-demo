package com.ring.front.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ring.front.web.util.WebUtils;

/**
 * @desc 过滤器
 * @author ganchungen
 * @since 2014-10-9
 */
public class LoginDevFilter implements Filter {
	protected Log logger = LogFactory.getLog(this.getClass());
	protected FilterConfig filterConfig;
	private String redirectURL = null;//重定向页面
	@Override
	public void destroy() {
		this.filterConfig = null;
	}
	@Override
	public void init(FilterConfig filterCfg) throws ServletException {
		this.filterConfig = filterCfg;
		redirectURL = "/index.jsp";
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String requestUrl = httpRequest.getRequestURI();
		if(requestUrl.contains(".do")){
			logger.info("requestUrl---------->"+requestUrl);
		}
			
		String userName = WebUtils.getCurrentUser();
		logger.info("LOGIN-USER-NAME=="+userName);
		if("".equals(userName) || null==userName){
//			logger.info("--------session信息失效,请重新登陆......-----------");
			if(requestUrl.contains("/css/") || 
			   requestUrl.contains("/images/") ||
			   requestUrl.contains("/js/") ||
			   requestUrl.contains("/jsp/"))
			{
//				logger.info("合法资源请求,不做过滤。");
				chain.doFilter(request, response);
				return;
			}else if("/login.do".equals(requestUrl)){
				logger.info("登陆请求,不做过滤.");
				chain.doFilter(request, response);
				return;
			}else if("/register.do".equals(requestUrl)){
				logger.info("注册请求,不做过滤.");
				chain.doFilter(request, response);
				return;
			}else if("/index.jsp".equals(requestUrl)){
				logger.info("首页请求,不做过滤.");
				chain.doFilter(request, response);
				return;
			}else{
				logger.info("requestUrl---------->"+requestUrl);
				logger.warn("非法请求,转回首页...........");
				httpResponse.sendRedirect(redirectURL);
				return;
			}
		}else{
			logger.info("用户已登陆.............");
			chain.doFilter(request, response);
			return;
		}
//		logger.info("111111111111");
//		chain.doFilter(request, response);
	}
}
