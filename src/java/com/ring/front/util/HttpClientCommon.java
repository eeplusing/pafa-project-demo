package com.ring.front.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @desc 短信发送公共类
 * @author ganchungen
 * @since 2014-10-4
 */
public class HttpClientCommon {
	
	/**
	 * @desc POST方法
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String post(String url,String data) throws Exception{
		return post(url,data,"application/xml");
	}
	/**
	 * httpClient 响应数据
	 * @param url
	 * @param data
	 * @param contentType
	 * @return String
	 */
	public static String post(String url,String data,String contentType) throws Exception{
		StringBuffer buf = new StringBuffer() ;
		HttpClient httpClient = new HttpClient();
//		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
		managerParams.setParameter("http.protocol.content-charset","UTF-8");
		managerParams.setConnectionTimeout(10000) ;
//		managerParams.setSoTimeout(ConfigConstants.CONN_TIMEOUT) ;
		PostMethod postMethod = new PostMethod(url) ;
		RequestEntity requestEntity = new StringRequestEntity(data, contentType,"UTF-8"); 
		postMethod.setRequestEntity(requestEntity) ;
		try{
			int responseCode = httpClient.executeMethod(postMethod) ;
			System.out.println("===="+responseCode);
			if(responseCode == HttpStatus.SC_OK){
				InputStream resStream = postMethod.getResponseBodyAsStream(); 
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8")); 
				StringBuffer resBuffer = new StringBuffer(); 
				String resTemp = ""; 
				while((resTemp = br.readLine()) != null){ 
					resBuffer.append(resTemp); 
				} 
				String response = resBuffer.toString(); 
				buf.append(response) ;
			}else{
				throw new RuntimeException("Bad response status, " + responseCode);
			}
			return buf.toString() ;
		}finally{
			postMethod.releaseConnection() ;
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
//			((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
		}
	}
	
	
	public static String get(String url, String content) {
		if(content == null || "".equals(content)){
			content = "text/html";
		}
		String result = "";
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// set proxy
		// set charset
		client.getParams().setParameter("http.protocol.content-charset", "utf-8");
		// set http client timeout
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		// Create a method instance.
		GetMethod getMethod = new GetMethod(url);
		getMethod.addRequestHeader("Connection","close");
		// Provide custom retry handler is necessary
		getMethod.addRequestHeader("Content-type", "text/html; charset=" + "utf-8");
		// set get method timeout
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
		// set query string
		InputStream in = null;
		BufferedReader buffer = null;
		try {
			// Execute the method.
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + getMethod.getStatusLine());
			}
			// Read the response body.
	//		byte[] responseBody = getMethod.getResponseBody();
			 StringBuffer temp = new StringBuffer();
	         in = getMethod.getResponseBodyAsStream();
	         buffer = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	         for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
	                temp = temp.append(tempstr);
	            
	         result = temp.toString().trim();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
//			result = new String(responseBody);
		} catch (HttpException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			getMethod.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return result;
	}
}


