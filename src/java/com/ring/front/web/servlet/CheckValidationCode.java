package com.ring.front.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验验证码.
 * @author luohuan
 * 2014/10/20
 */
public class CheckValidationCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckValidationCode() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String inputCode = request.getParameter("inputCode");
		Object generateCodeObj = request.getSession().getAttribute("randNum");
		String generateCode = "";
		if (generateCodeObj != null) {
			generateCode = generateCodeObj.toString();
		}
		System.out.println("inputCode = " + inputCode + ", generateCode = " + generateCode);
		// 验证码是否输入正确
		if (inputCode.equalsIgnoreCase(generateCode)) {
			response.getWriter().write("validate");
		} else {
			response.getWriter().write("invalidate");
		}
	}
}
