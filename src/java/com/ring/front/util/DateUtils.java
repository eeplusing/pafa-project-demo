package com.ring.front.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @desc 日期工具类
 * @author ganchungen
 * @since 2014-10-4
 */
public class DateUtils {
	protected static Log logger = LogFactory.getLog(DateUtils.class);
	
	/**
	 * @desc 获取时间戳
	 * @param void
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimestamp() {
		String timestamp = "";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timestamp = dateFormat.format(date);
		logger.info("timestamp : " + timestamp);
		return timestamp;
	}

	/**
	 * 获取YYYYMMDD字符串
	 * @return YYYYMMDD
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}
	

	// Add millisecond
	public static Date addMillisecond(Date dt, int millisecond) {
		return addSecond(dt, (long) millisecond);
	}

	public static Date addMillisecond(Date dt, long millisecond) {
		Date newDate = new Date();
		newDate.setTime(dt.getTime() + millisecond);
		return newDate;
	}

	// add second
	public static Date addSecond(Date dt, int second) {
		return addSecond(dt, (long) second);
	}

	public static Date addSecond(Date dt, float second) {
		return addSecond(dt, (double) second);
	}

	public static Date addSecond(Date dt, long second) {
		return addMillisecond(dt, 1000L * second);
	}

	public static Date addSecond(Date dt, double second) {
		Double millisecond = new Double(1000.0 * second);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add minute
	public static Date addMinute(Date dt, int minute) {
		return addMinute(dt, (long) minute);
	}

	public static Date addMinute(Date dt, float minute) {
		return addMinute(dt, (double) minute);
	}

	public static Date addMinute(Date dt, long minute) {
		return addMillisecond(dt, 1000L * 60L * minute);
	}

	public static Date addMinute(Date dt, double minute) {
		Double millisecond = new Double(1000.0 * 60.0 * minute);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add hour
	public static Date addHour(Date dt, int hour) {
		return addHour(dt, (long) hour);
	}

	public static Date addHour(Date dt, float hour) {
		return addHour(dt, (double) hour);
	}

	public static Date addHour(Date dt, long hour) {
		return addMillisecond(dt, 1000L * 60L * 60L * hour);
	}

	public static Date addHour(Date dt, double hour) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * hour);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add day
	public static Date addDay(Date dt, int day) {
		return addDay(dt, (long) day);
	}

	public static Date addDay(Date dt, float day) {
		return addDay(dt, (double) day);
	}

	public static Date addDay(Date dt, long day) {
		return addMillisecond(dt, 1000L * 60L * 60L * 24L * day);
	}

	public static Date addDay(Date dt, double day) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * 24.0 * day);
		return addMillisecond(dt, millisecond.longValue());
	}

	// add month
	public static Date addMonth(Date dt, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	public static Date addMonth(Date dt, float month) {
		return addMonth(dt, (double) month);
	}

	public static Date addMonth(Date dt, long month) {
		return addMonth(dt, (new Long(month)).intValue());
	}

	public static Date addMonth(Date dt, double month) {
		double floorMonth = Math.floor(month);
		double decimalMonth = month - floorMonth;
		dt = addMonth(dt, (new Double(floorMonth)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		Date nextdt = cal.getTime();
		long monthMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = (double) monthMillisecond * decimalMonth;
		return addMillisecond(dt, (long) millisecond);
	}

	// add month by lancy
	public static Date addMonth(String dt, int month) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(parseString(dt));
		} catch (Exception e) {
			logger.warn(e, e);
		}
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	// add year
	public static Date addYear(Date dt, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
		return cal.getTime();
	}

	public static Date addYear(Date dt, float year) {
		return addYear(dt, (double) year);
	}

	public static Date addYear(Date dt, long year) {
		return addYear(dt, (new Long(year)).intValue());
	}

	public static Date addYear(Date dt, double year) {
		double floorYear = Math.floor(year);
		double decimalYear = year - floorYear;
		dt = addYear(dt, (new Double(floorYear)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		Date nextdt = cal.getTime();
		long yearMillisecond = nextdt.getTime() - dt.getTime();
		double millisecond = (double) yearMillisecond * decimalYear;
		return addSecond(dt, (long) millisecond);
	}

	//字符串转 date
	@SuppressWarnings("unused")
	public static Date strToDate(String strDate, String format) {
		if (strDate == null) {
			return null;
		}
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		if (strDate != null) {
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		}
		return null;
	}

	//获得日期 的字符串
	public static String getStringDate(Date date, String format) {
		if (date == null) {
			date = new Date();
		}
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**
	 * 将日期字符串或时间转换成时间类型 日期字符串中的日期分隔符可是是"/",".","-"，返回时间具体到秒.
	 * 
	 * @param str
	 * @return Date
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Date parseString(String dateString) throws ParseException {
		// return stringToCalendar(str, fmt).getTime();
		if (StringUtils.isBlank(dateString)
				|| "null".equals(dateString.toLowerCase())) {
			throw new IllegalArgumentException("The date string is null!");
		}

		if (dateString.indexOf("-") != -1) {
			dateString = dateString.replaceAll("-", "/");
		} else if (dateString.indexOf(".") != -1) {
			dateString = dateString.replace('.', '/');
		}
		String fmt = "yyyy/MM/dd";
		if (dateString.indexOf(":") != -1) {
			fmt = "yyyy/MM/dd HH:mm:ss";
		}

		DateFormat df = new SimpleDateFormat(fmt);
		return df.parse(dateString);

	}
	/**
	 * 获得两个自然日后的日期 字符串
	 * @return 
	 */
	public static String getT2DateStr(){
		return getStringDate(addDay(strToDate(getDate(),"yyyyMMdd"), 2),"yyyy-MM-dd");
	}
	/**
	 * 获得两个自然日后的日期
	 * @return
	 */
	public static Date getT2Date(){
		return strToDate(getStringDate(addDay(strToDate(getDate(),"yyyyMMdd"), 2),"yyyy-MM-dd"),"yyyy-MM-dd");
	}
	public static void main(String args[]){
		String dd = "20140227";
		System.out.println(getStringDate(addDay(strToDate(dd,"yyyyMMdd"), 2), "yyyy-MM-dd"));
		System.out.println(getT2Date());
		System.out.println(getT2DateStr());
	}
}