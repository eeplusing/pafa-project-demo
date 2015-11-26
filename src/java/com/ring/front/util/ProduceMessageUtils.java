package com.ring.front.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @desc 报文组装工具类
 * @author ganchungen
 * @since 2014-10-1
 *
 */
public class ProduceMessageUtils {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	public static String ROOT_HEAD = "<?xml version=\"1.0\" encoding = \"GBK\"?><BEDC><Message>";
	public static String ROOT_TAIL = "</Message></BEDC>";
	
	/**
	 * @desc 生成报文头
	 * @param MessageHeadDTO
	 * @return String
	 * @add by ganchungen
	 */
//	public static String createCommonMsgHead(MessageHeadDTO dto){
//		System.out.println("MessageHeadDTO--->"+dto);
//		String messageHeadStr = "<commHead><tranCode>"   + dto.getTranCode()  +
//							    "</tranCode><cifMaster>" + dto.getCifMaster() +
//							    "</cifMaster><entSeqNo>" + dto.getEntSeqNo()  +
//							    "</entSeqNo><tranDate>"  + dto.getTranDate()  +
//							    "</tranDate><tranTime>"  + dto.getTranTime()  +
//							    "</tranTime><retCode> </retCode><entUserId>" +dto.getEntUserId() +
//							    "</entUserId><password><![CDATA[" + dto.getPassword() +
//							    "]></password></commHead>";
//		System.out.println(messageHeadStr);
//		return messageHeadStr;
//	}
	
	/**
	 * @desc 生成查询余额报文体
	 * @param QryBalanceSendDTO
	 * @return String
	 * @add by ganchungen
	 */
//	public static String createQryBalMsgBody(QryBalanceSendDTO dto){
//		String msgBodyStr = "<Body><account>"+dto.getAcctNum()+"</account></Body>";
//		System.out.println(msgBodyStr);
//		return msgBodyStr;
//	}
	
	/**
	 * @desc 生成查询余额报文体
	 * @param QryTradeDetailSendDTO
	 * @return String
	 * @add by ganchungen
	 */
//	public static String createQryTradeDetailMsgBody(QryTradeDetailSendDTO dto){
//		String msgBodyStr = "<Body><account>"         + dto.getAccount() + 
//							"</account><queryType>"   + dto.getQueryType() +
//							"</queryType><beginDate>" + dto.getBeginDate() +
//							"</beginDate><endDate>"   + dto.getEndDate() + 
//							"</endDate></Body>";
//		System.out.println(msgBodyStr);
//		return msgBodyStr;
//	}
}
