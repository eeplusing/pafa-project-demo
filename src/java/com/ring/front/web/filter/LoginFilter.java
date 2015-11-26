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

import com.ring.front.util.MapUtils;
import com.ring.front.web.util.WebUtils;

/**
 * @desc 过滤器
 * @author ganchungen
 * @since 2014-10-9
 */
public class LoginFilter implements Filter {
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
		String userName = WebUtils.getCurrentUser();
//		if(requestUrl.contains(".do") || requestUrl.contains(".jsp")){
//			logger.info("requestUrl---------->"+requestUrl);
//			logger.info("LOGIN-USER-NAME=="+userName);
//		}
		
		if(MapUtils.isContionNonLoginUrl(requestUrl)){
			logger.info("不用登陆就能过的请求.........");
			chain.doFilter(request, response);
			return;
		}else{
			if("".equals(userName) || null==userName){
				if(MapUtils.isContionResourceUrl(requestUrl)){
					chain.doFilter(request, response);
					return;
				}else if(MapUtils.isContionNonLoginJsp(requestUrl)){
					logger.info("不用登陆就能登陆的请求,不做过滤.");
					chain.doFilter(request, response);
					return;
				}else{
					logger.info("requestUrl---------->"+requestUrl);
					logger.warn("非法请求,转回登陆页...........");
					httpResponse.sendRedirect(redirectURL);
					return;
				}
			}else{
//				logger.info("用户已登陆.............");
				chain.doFilter(request, response);
				return;
			}
		}
//		chain.doFilter(request, response);
	}
}
