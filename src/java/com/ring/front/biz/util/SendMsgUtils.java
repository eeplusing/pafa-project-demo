package com.ring.front.biz.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ring.front.util.HttpClientCommon;

/**
 * @desc 短信发送工具类
 * @author ganchungen
 * @since 2014-10-9
 */
public class SendMsgUtils {
	protected static Log logger = LogFactory.getLog(SendMsgUtils.class);
	
	private static final String app_id = "237763650000037644";//应用ID 必填参数
	private static final String app_secret = "6aba7ee03733a4be65f3100c5a914ba0";//应用密码 必填参数
	private static String template_id = "91002626";//短信模板ID 必填参数
//	static String acceptor_tel = "18922250044";//必填参数
//	static String template_param = "{\"param1\": \"13428920575\",\"param2\": \"123456\",\"param3\": \"120\"}";//必填参数
	static String timestamp = "";//时间戳  必填参数
	static String sign = "";//可选参数	//加密串
	
	private static final String TIME_OUT = "120";
	private static final String smsUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";//短信发送地址
	private static final String REDIRECT_URI = "https://oauth.api.189.cn/emp/" ;//暂时没有用
	private static final String access_token_url = "https://oauth.api.189.cn/emp/oauth2/access_token";//获取token地址
	
	public static void main(String[] args) throws Exception {
//		String acceptor_tel = "13428920575";
//		String val_code = "111111";
//		SendMsgInfoDTO dto = new SendMsgInfoDTO();
//		dto.setAcceptor_tel(acceptor_tel);
//		dto.setVal_code(val_code);
//		sendSms(dto);
		String s = getToken();
		JSONObject json = new JSONObject(s);
		System.out.println("access_token=="+json.getString("access_token"));
	}
	
	/**
	 * @desc 验证码短信发送
	 * @param acceptor_tel
	 * @return
	 * @throws Exception
	 */
//	public static String sendSms(SendMsgInfoDTO dto) throws Exception {
//		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		timestamp = dateFormat.format(date);
//		String access_token_str = getToken();
//		JSONObject json = new JSONObject(access_token_str);
//		String access_token = json.getString("access_token");
//		logger.info("access_token==="+access_token+ "|| timestamp=="+timestamp);
//		String acceptor_tel = dto.getAcceptor_tel();
//		String valCode = dto.getVal_code();
//		String postEntity = "app_id=" + app_id
//				          + "&acceptor_tel=" + acceptor_tel
//				          + "&access_token=" + access_token
//				          + "&template_id=" + template_id
//				          + "&template_param=" + getJsonParm(acceptor_tel,valCode,TIME_OUT)
//				          + "&timestamp=" + URLEncoder.encode(timestamp,"UTF-8");
//		
//		return HttpClientCommon.post(smsUrl,postEntity);
//	}
	
	/**
	 * @desc 生成JSON格式模板参数
	 * @param acceptor_tel
	 * @param valCode
	 * @param timeOut
	 * @return String
	 */
	public static String getJsonParm(String acceptor_tel, String valCode, String timeOut){
		String template_param = "";
//		String[] array = acceptor_tel.split(",");
//		for(int  i = 0 ;i < array.length; i++){
//			String telephone = sendSms(array[i]);
//			logger.info(telephone);
//		}
		JSONObject json = new JSONObject();
		try {
			json.put("param1", acceptor_tel);
			json.put("param2", valCode);
			json.put("param3", timeOut);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		template_param = json.toString();
		logger.info("template_param=="+template_param);
		return template_param;
	}
	
	/**
	 * @desc token获取
	 * @return String
	 * @author ganchungen
	 */
	public static String getToken() {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("app_id", app_id));
		formparams.add(new BasicNameValuePair("app_secret", app_secret));
		formparams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));
		logger.info("token获取开始......");
		formparams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		return doPost(access_token_url, formparams);
	}
	
	/**
	 * @desc 发送post请求
	 * @param req_url
	 * @param form_params
	 * @return String
	 * @author ganchungen
	 */
	public static String doPost(String req_url, List<NameValuePair> form_params) {
		HttpClient httpclient = getHttpsClient();
		HttpPost httppost;
		HttpResponse response;
		try {
			URI uri = new URI(req_url);
			logger.info("http post uri = " + uri);
			httppost = new HttpPost(uri);
			HttpEntity entity = new UrlEncodedFormEntity(form_params, "UTF-8");
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			int status = response.getStatusLine().getStatusCode();
			logger.info("http response status code = " + status);
			if (status == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			} else if(status == 302 || status == 307) {
				logger.info("the http response status is : "+status);
				for(int i = 0; i < response.getAllHeaders().length; i++) {
					logger.info("params of respons'" + i + ":" +response.getAllHeaders()[i]);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 * @desc 获取https请求
	 * @return httpsClient
	 * @author ganchungen
	 */
	private static HttpClient getHttpsClient() {
		HttpClient httpclient = new DefaultHttpClient();
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[]{manager}, null);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SSLSocketFactory sslSocketFactory =	new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		ClientConnectionManager clientConnectionManager = httpclient.getConnectionManager();
		SchemeRegistry schemeRegistry = clientConnectionManager.getSchemeRegistry();
		schemeRegistry.register(new Scheme("https", 443, sslSocketFactory));
		return httpclient;
	}
	
	private static X509TrustManager manager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
		throws CertificateException {}
		public void checkServerTrusted(X509Certificate[] xcs, String string)
		throws CertificateException {}
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
}
