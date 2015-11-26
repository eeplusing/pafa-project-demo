package com.ring.front.web.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 工具类: 处理对接银联系统的字段信息
 * 
 * @author luohuan
 * @since 2014/11/20
 */
public class UnionPayUtil {
	
	// 定义日志对象
	protected static Log logger = LogFactory.getLog(UnionPayUtil.class);
	
	static final TimeZone timeZone;
	static {
		// 设置时区为GMT+8
		timeZone = TimeZone.getTimeZone("GMT+8");
		TimeZone.setDefault(timeZone);
	}

	/**
	 * 获取交易日期 (格式: YYYYMMDD)
	 * 
	 * @return
	 */
	public static String getTradeDate() {
		StringBuilder sb = new StringBuilder();
		Calendar calendar = new GregorianCalendar(timeZone);
		int month= calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		// StringBuilder构建日期格式
		return sb.append(calendar.get(Calendar.YEAR))
				.append(month<10?"0"+month:month)
				.append(day<10?"0"+day:day).toString();
	}
	
	/**
	 * 获取交易金额(格式12位数字,精确到分)
	 * 
	 * @param amount
	 *            转账金额(双精度浮点型的字串)
	 * @return
	 */
	public static String getTradeAmount(String amount) {
		logger.info("进入获取交易金额的方法中.amount: " + amount);
		StringBuilder sb = new StringBuilder();
		if (amount.indexOf(".") != -1) {
			String integer = amount.substring(0, amount.indexOf("."));
			String decimal = amount.substring(amount.indexOf(".") + 1);
			for (int i = integer.length(), j = 10 - i;j >0;j--) {
				sb.append("0");
			}
			sb.append(integer).append(decimal);
			for (int i = decimal.length(), j = 2 - i;j >0;j--) {
				sb.append("0");
			}
		} else {
			int accountLen = amount.length();
			for (int i = 10 - accountLen;i >0;i--) {
				sb.append("0");
			}
			sb.append(amount).append("00");
		}
		return sb.toString();
	}
	
	
	static final int BILL_NUMBER_LENGTH = 16;
	/**
	 * 获取订单编号 (格式: 16位数字.订单号的第5-9位必须是商户号的最后5位)
	 * 
	 * @param merId
	 *            商户号
	 * @param int           
	 *  		      序列号
	 * @return
	 */
	public static String getOrderId(String merId,int sequence) {
		StringBuilder sb = new StringBuilder("00");
		Calendar calendar = new GregorianCalendar(timeZone);
		int year = calendar.get(Calendar.YEAR);
		// 获取商户号的最后4位
		String lastFourChars = merId.substring(merId.length()-4);
		sb.append(year).append(lastFourChars);
		int remaind = BILL_NUMBER_LENGTH - 10 - String.valueOf(sequence).length();
		for (;remaind >0;remaind--) {
			sb.append("0");
		}
		sb.append(sequence);
		return sb.toString();
	}
	
   /**
    * 获取4位年份
    * 
    * @return
    */
   public static int getFullYear() {
	   Calendar calendar = new GregorianCalendar(timeZone);
	   return calendar.get(Calendar.YEAR);
   }

/*   public static void main(String[] args) {
	   // System.out.println(getTradeAmount("500000.5"));
   }*/
}