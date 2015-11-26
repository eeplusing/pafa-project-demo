package com.ring.front.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class RenderUtils {

	//-- header 常量定义 --//
	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 直接输出内容的简便函数.
	 * eg.
	 * render(sresponse,"text/plain", "hello", "encoding:GBK");
	 * render(sresponse,"text/plain", "hello", "no-cache:false");
	 * render(sresponse,"text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(HttpServletResponse sresponse,final String contentType, final String content) {
		HttpServletResponse response = initResponseHeader(sresponse,contentType);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse sresponse,final String text, final String... headers) {
		render(sresponse,ServletUtils.TEXT_TYPE, text);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse sresponse,final String html, final String... headers) {
		render(sresponse,ServletUtils.HTML_TYPE, html);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse sresponse,final String xml, final String... headers) {
		render(sresponse,ServletUtils.XML_TYPE, xml);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse sresponse,final String jsonString, final String... headers) {
		render(sresponse,ServletUtils.JSON_TYPE, jsonString);
	}

	/**
	 * 直接输出JSON,使用FastJson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse sresponse,final Object data) {
		HttpServletResponse response = initResponseHeader(sresponse,ServletUtils.JSON_TYPE);
		
		try {
			response.getWriter().write(JSONObject.toJSONString(data));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(HttpServletResponse response,final String contentType) {
		//分析headers参数
		String encoding = DEFAULT_ENCODING;
		
		//设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		
		response.setContentType(fullContentType);
		ServletUtils.setNoCacheHeader(response);
		
		return response;
	}
}
