package com.ring.front.webservice.guozhengtong;

/**
 * 国政通接口的测试籄1�7�1�7�1�7�1�7 * @author john
 *
 */
public class TestWeb {
	
	public static void main(String[] args) throws Exception {
		//获得WebServices的代理对豄1�7�1�7�1�7�1�7		
		QueryValidatorServicesProxy proxy = new QueryValidatorServicesProxy();
		proxy.setEndpoint("http://gboss.id5.cn/services/QueryValidatorServices");
		QueryValidatorServices service =  proxy.getQueryValidatorServices();
		//对调用的参数进行加密
		String key = "12345678";
		String userName = Des2.encode(key, "HMJJService");
		String password = Des2.encode(key, "HMJJService_BxB5j(mN");
		//System.setProperty("javax.net.ssl.trustStore", "keystore");
		//查询返回结果
		String resultXML = "";
				
		/*------身份信息核查比对-------*/
		String datasource = Des2.encode(key,"1A020201");
		//单条
		String param = "张三1,372525198308281282";
		resultXML = service.querySingle(userName, password, datasource, Des2.encode(key, param));
		
		//解密
		resultXML = Des2.decodeValue(key, resultXML);
		
		System.out.println("resultXML = " + resultXML);
		System.out.println("param = " + param);
		
		/**
	<data>
	  <message>
	    <status>0</status>
	    <value>处理成功</value>
	  </message>
	  <policeCheckInfos>
	    <policeCheckInfo name="张三" id="372525198308281282">
	      <message>
	        <status>0</status>
	        <value>查询成功</value>
	      </message>
	      <name desc="姓名">张三</name>
	      <identitycard desc="身份证号">372525198308281282</identitycard>
	      <compStatus desc="比对状态">3</compStatus>
	      <compResult desc="比对结果">一致</compResult>
	      <no desc="唯一标识" />
	    </policeCheckInfo>
	  </policeCheckInfos>
	</data>
		 */
		
		/**
	<data>
	  <message>
	    <status>-9967</status>
	    <value>您无权查询数据（测试量已用完）</value>
	  </message>
	</data>
		 */

	}
}
