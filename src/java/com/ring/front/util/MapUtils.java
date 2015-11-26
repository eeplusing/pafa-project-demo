package com.ring.front.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @desc map静态工具类
 * @author ganchungen
 * @since2014-10-27
 */
public abstract class MapUtils {
	protected final static Log logger = LogFactory.getLog(MapUtils.class);
	
	/**
	 * 存储不用登陆就能请求的.do
	 */
	private static Map<String,String> nonLogioUrlMap = new HashMap<String,String>();
    static{
    	nonLogioUrlMap.put("function0", "/favicon.ico");//icon
    	nonLogioUrlMap.put("function1", "/light/login");//登陆请求
    	nonLogioUrlMap.put("function2", "/light/regist");//注册请求
    	nonLogioUrlMap.put("function4", "/light/jihuo.do");//激活注册请求
    	nonLogioUrlMap.put("function5", "/mobileAuth.do");//验证手机号请求
    	nonLogioUrlMap.put("function6", "/check-session-mobileCode.do");//验证短信请求
    	nonLogioUrlMap.put("function7", "/sendSms.do");//发送短信请求
    	nonLogioUrlMap.put("function8", "/check-mobile.do");//验证手机号码是否已被注册请求
    	nonLogioUrlMap.put("function9", "/check-smscode.do");//
    	nonLogioUrlMap.put("function10", "/send-validate-code.do");//
    	nonLogioUrlMap.put("function11", "/queryProductInfo.do");//查询产品详情
    	nonLogioUrlMap.put("function12", "/queryPassword.do");//查询密码
    	nonLogioUrlMap.put("function13", "/hangmei.ico");//icon
    	nonLogioUrlMap.put("function14", "/check-hmCode.do");//查询航美码
    	nonLogioUrlMap.put("function15", "/qryMobile4fogetPsw.do");//查询手机号
    	nonLogioUrlMap.put("function16", "/forgetPassword.do");//忘记密码流程提交
    	nonLogioUrlMap.put("function17", "/invest-qry-product-list.do");//我要投资查询产品列表
    	
    	nonLogioUrlMap.put("function18", "/UP-Background-Handler.do");// 银联创建订单
    	// For test
    	nonLogioUrlMap.put("function19", "/buyProductUseBank.do");// 银联创建订单
    	nonLogioUrlMap.put("function20", "/getNews.do");// 获取新闻内容
    	nonLogioUrlMap.put("function21", "/getNewsTitleList.do");// 获取新闻列表
    	nonLogioUrlMap.put("function22", "/joinUs.do");// 招聘信息列表
    	nonLogioUrlMap.put("function23", "/queryLimitSellInfos.do");// 查询最新交易记录
    }
    
    public static boolean isContionNonLoginUrl(String url){
    	logger.info("url----------"+url);
    	return nonLogioUrlMap.containsValue(url);
	}
    
    /**
	 * 存储资源请求的map
	 */
	private static Map<String,String> resourceMap = new HashMap<String,String>();
    static{
    	resourceMap.put("resource1", "/css/");
    	resourceMap.put("resource2", "/images/");
    	resourceMap.put("resource3", "/js/");
    	resourceMap.put("resource4", "/news/");
    	resourceMap.put("resource5", "/home/");
    	resourceMap.put("resource6", "/servicecenter/");
    	resourceMap.put("resource7", "/helpcenter/");
    	resourceMap.put("resource8", "/password/");
    	resourceMap.put("resource9", "/investment/");
    	resourceMap.put("resource10", "/safeguard/");
    }
    public static boolean isContionResourceUrl(String url){
    	boolean result = false;
    	for(Map.Entry<String, String> entry : resourceMap.entrySet()){
//    		System.out.println(entry.getKey()+": "+entry.getValue());    
    		if(url.contains(entry.getValue())){
    			result = true;
    			break;
    		}
    	} 
    	return result;
	}
    
    /**
	 * 存储合法jsp请求的map
	 */
	private static Map<String,String> nonLoginJspMap = new HashMap<String,String>();
	static{
		nonLoginJspMap.put("screen1", "/GenValidationCode.screen");
		nonLoginJspMap.put("screen2", "/CheckValidationCode.screen");
		nonLoginJspMap.put("nonLoginJsp1", "/index.jsp");
		nonLoginJspMap.put("nonLoginJsp2", "/userlogin.jsp");
    	nonLoginJspMap.put("nonLoginJsp3", "/userregister.jsp");
    	nonLoginJspMap.put("nonLoginJsp4", "/noticeinfos.jsp");
    	nonLoginJspMap.put("nonLoginJsp5", "/products/proinfos.jsp");
    	nonLoginJspMap.put("nonLoginJsp6", "/pro1details.jsp");
    	nonLoginJspMap.put("nonLoginJsp7", "/pro2details.jsp");
    	nonLoginJspMap.put("nonLoginJsp8", "/pro3details.jsp");
    }
	public static boolean isContionNonLoginJsp(String url){
		return nonLoginJspMap.containsValue(url);
	}
	
	/*--------------- 存储过程通状态信息 Start ---------------*/
	/**
	 * 存储处理状态
	 */
	private static Map<String, String> dealStatusMap = new HashMap<String, String>();
	static {
		dealStatusMap.put("0", "查询成功");
		dealStatusMap.put("1", "未查到数据");
		dealStatusMap.put("2", "查询失败");
	}
	public static String getDealMessages(String status) {
		if (status == null || "".equals(status)) {
			return "无";
		} else {
			return dealStatusMap.get(status);
		}
	}
	
	/**
	 * 存储查询状态的map
	 */
	private static Map<String, String> queryStatusMap = new HashMap<String, String>();
	static {
		queryStatusMap.put("0", "处理成功");
		queryStatusMap.put("2", "查询失败");
		queryStatusMap.put("-9999", "参数数据不正确(部分参数为空)");
		queryStatusMap.put("-9998", "您的用户信息错误（用户名不存在）");
		queryStatusMap.put("-9997", "您无权查询数据");
		queryStatusMap.put("-9996", "参数请求数据过长");
		queryStatusMap.put("-9995", "该产品已暂停使用");
		queryStatusMap.put("-9994", "参数数据加密错误");
		queryStatusMap.put("-990", "系统异常");
		queryStatusMap.put("9999", "未查到数据");
		queryStatusMap.put("-9919", "参数数据不正确(参数格式不正确)");
		queryStatusMap.put("-9929", "参数数据不正确(参数个数不正确)");
		queryStatusMap.put("-9917", "您无权查询数据(ip 无权限)");
		queryStatusMap.put("-9927", "您无权查询数据(没有订购该产品)");
		queryStatusMap.put("-9937", "您无权查询数据(产品状态是开始状态)");
		queryStatusMap.put("-9947", "您无权查询数据(产品状态是暂停状态)");
		queryStatusMap.put("-9957", "您无权查询数据(产品状态是终止状态)");
		queryStatusMap.put("-9967", "您无权查询数据(测试量已经用完)");
		queryStatusMap.put("-9977", "您无权查询数据(账户余额不足)");
	}
	public static String getqueryMessages(String status) {
		System.out.println(status);
		if (status == null || "".equals(status)) {
			return "无";
		} else {
			return queryStatusMap.get(status);
		}
	}
	
	/**
	 * 存储对比状态的map
	 */
	private static Map<String, String> compareStatusMap = new HashMap<String, String>();
	static {
		compareStatusMap.put("1", "库中无此号，请到户籍所在地进行核实!");
		compareStatusMap.put("2", "不一致");
		compareStatusMap.put("3", "一致");
	}
	public static String getCompareMessages(String status) {
		if (status == null || "".equals(status)) {
			return "无";
		} else {
			return compareStatusMap.get(status);
		}
	}
	/*--------------- 存储过程通状态信息 End ---------------*/
	
}
