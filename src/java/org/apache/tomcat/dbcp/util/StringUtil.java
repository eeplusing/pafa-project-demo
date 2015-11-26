package org.apache.tomcat.dbcp.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
 

public class StringUtil {
	private static int[] a2eG = { 0, 1, 2, 3, 55, 45, 46, 47, 22, 5, 37, 11,
		12, 13, 14, 15, 16, 17, 18, 19, 60, 61, 50, 38, 24, 25, 63, 39, 28,
		29, 30, 31, 64, 79, 127, 123, 91, 108, 80, 125, 77, 93, 92, 78,
		107, 96, 75, 97, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249,
		122, 94, 76, 126, 110, 111, 124, 193, 194, 195, 196, 197, 198, 199,
		200, 201, 209, 210, 211, 212, 213, 214, 215, 216, 217, 226, 227,
		228, 229, 230, 231, 232, 233, 74, 224, 90, 95, 109, 121, 129, 130,
		131, 132, 133, 134, 135, 136, 137, 145, 146, 147, 148, 149, 150,
		151, 152, 153, 162, 163, 164, 165, 166, 167, 168, 169, 192, 106,
		208, 161, 7, 32, 33, 34, 35, 36, 21, 6, 23, 40, 41, 42, 43, 44, 9,
		10, 27, 48, 49, 26, 51, 52, 53, 54, 8, 56, 57, 58, 59, 4, 20, 62,
		225, 65, 66, 67, 68, 69, 70, 71, 72, 73, 81, 82, 83, 84, 85, 86,
		87, 88, 89, 98, 99, 100, 101, 102, 103, 104, 105, 112, 113, 114,
		115, 116, 117, 118, 119, 120, 128, 138, 139, 140, 141, 142, 143,
		144, 154, 155, 156, 157, 158, 159, 160, 170, 171, 172, 173, 174,
		175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187,
		188, 189, 190, 191, 202, 203, 204, 205, 206, 207, 218, 219, 220,
		221, 222, 223, 234, 235, 236, 237, 238, 239, 250, 251, 252, 253,
		254, 255 };

private static int[] e2aG = { 0, 1, 2, 3, 156, 9, 134, 127, 151, 141, 142,
		11, 12, 13, 14, 15, 16, 17, 18, 19, 157, 133, 8, 135, 24, 25, 146,
		143, 28, 29, 30, 31, 128, 129, 130, 131, 132, 10, 23, 27, 136, 137,
		138, 139, 140, 5, 6, 7, 144, 145, 22, 147, 148, 149, 150, 4, 152,
		153, 154, 155, 20, 21, 158, 26, 32, 160, 161, 162, 163, 164, 165,
		166, 167, 168, 91, 46, 60, 40, 43, 33, 38, 169, 170, 171, 172, 173,
		174, 175, 176, 177, 93, 36, 42, 41, 59, 94, 45, 47, 178, 179, 180,
		181, 182, 183, 184, 185, 124, 44, 37, 95, 62, 63, 186, 187, 188,
		189, 190, 191, 192, 193, 194, 96, 58, 35, 64, 39, 61, 34, 195, 97,
		98, 99, 100, 101, 102, 103, 104, 105, 196, 197, 198, 199, 200, 201,
		202, 106, 107, 108, 109, 110, 111, 112, 113, 114, 203, 204, 205,
		206, 207, 208, 209, 126, 115, 116, 117, 118, 119, 120, 121, 122,
		210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222,
		223, 224, 225, 226, 227, 228, 229, 230, 231, 123, 65, 66, 67, 68,
		69, 70, 71, 72, 73, 232, 233, 234, 235, 236, 237, 125, 74, 75, 76,
		77, 78, 79, 80, 81, 82, 238, 239, 240, 241, 242, 243, 92, 159, 83,
		84, 85, 86, 87, 88, 89, 90, 244, 245, 246, 247, 248, 249, 48, 49,
		50, 51, 52, 53, 54, 55, 56, 57, 250, 251, 252, 253, 254, 255 };

public static String asciiToEbcdic(String str) {
	if (str.length() == 0) {
		return str;
	} else {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			result.append((char) a2eG[(int) str.charAt(i)]);
		}
		return result.toString();
	}
}

public static String ebcdidToAscii(String str) {
	if (str.length() == 0) {
		return str;
	} else {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			result.append((char) e2aG[(int) str.charAt(i)]);
		}
		return result.toString();
	}
}

public static String asciiToChinese(String s) {
	char[] orig = s.toCharArray();
	byte[] dest = new byte[orig.length];
	for (int i = 0; i < orig.length; i++) {
		dest[i] = (byte) (orig[i] & 0xFF);
	}
	try {
		return new String(dest, "GBK");
	} catch (UnsupportedEncodingException e) {
		// TODO 自动生成 catch 块
		return s;
	}
}

public static String chineseToAscii(String s){
   try{
     byte[] orig = s.getBytes("GBK");
     char[] dest = new char[orig.length];
     for (int i = 0; i < orig.length; i++){
       dest[i] = (char) (orig[i] & 0xFF);
      }
     return new String(dest);
    } catch (UnsupportedEncodingException ex){
       return s;
    }
}
  /**
   * Turns array of bytes into string
   */
  public static String asHex(byte buf[]) {
    StringBuffer strbuf = new StringBuffer(buf.length * 2);

    for (int i = 0; i < buf.length; i++) {
      if ( ( (int) buf[i] & 0xff) < 0x10) {
        strbuf.append("0");
      }

      strbuf.append(Long.toString( (int) buf[i] & 0xff, 16));
    }

    return strbuf.toString();
  }

  /**
   * Turns string into array of bytes
   */
  public static byte[] asByte(String buf) {
    if (buf.length() % 2 == 1) {
      buf = "0" + buf;
    }
    int length = buf.length() / 2;
    byte[] bytebuf = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      String b = buf.substring(pos, pos + 2);
      bytebuf[i] = (byte) Integer.parseInt(b, 16);
    }
    return bytebuf;
  }
//	public static float toFloat(String value) {
//		float f = 0.0F;
//		try {
//			f = Float.parseFloat(value);
//		} catch (Exception e) {
//			PAAMSLogger.warn("金额格式错误:" + e.getMessage());
//		}
//		return f;
//	}

	public static Date toDate(String value) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(value);
		} catch (Exception e) {
			//PAAMSLogger.warn("日期格式错误:" + e.getMessage());
		}
		return date;
	}
	
	/**
	 * 将字符串按照指定长度进行拆分，并塞进list中。
	 * added by ex-chenyongming001 at 2008/11/20
	 * @param str 字符串
	 * @param len 指定长度
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List splitStrToList(String str,int len){
		List list = new Vector(str.length()/len);
		
		try {
			char[] bs = str.toCharArray();		
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < bs.length; i++){
				sb.append(bs[i]);
				
				/*
				 * 当截够len长度时放进list，并把sb清空。
				 */
				if((i % len) == (len-1)){					
					list.add(sb.toString());
					sb.setLength(0);				
				}else if(i == bs.length -1 ){
					/*
					 * 已经截到结尾，不管够不够长都放进list.
					 */
					list.add(sb.toString());
				}
			}
		} catch (Exception e) {
			//PAAMSLogger.error("", StringUtil.class, "splitStrToList", "将字符串["+str+"]按照指定长度["+len+"]进行拆分时发生错误!"+e.toString());
		}		
		return list;
	}
	/**
	 * 比较两个字符串是否相同
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isStringEqual(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}
	/**
	 * 将数据添拼凑至列表中指定位置的元素
	 * 
	 * @param lstContent
	 *            列表
	 * @param index
	 *            指定索引位置
	 * @param appendData
	 *            需要添加的数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void appendData2List(List lstContent, int index,
			String appendData) {
		if (lstContent == null || lstContent.size() <= 0
				|| lstContent.size() <= index) {
			return;
		}
		// 先取出列表指定位置的数据,将指定位置的数据加上需要添加的数据组成新的列表元素
		String target = (String) lstContent.get(index) + appendData;
		// 数据刷新,先移除再进行添加
		lstContent.remove(index);
		// 将新数据添加至指定位置
		lstContent.add(index, target);
	}
	
	
	public static String getNull(Object obj) {
		return obj == null ? " " : obj.toString();
	}
	/**
	 * 转换半角至全角字符
	 * 
	 * 转全角的函数(SBC case) 任意字符串 全角字符串
	 * 
	 * 全角空格为12288，半角空格为32 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @param input
	 * @return
	 */
	public static String transferSingle2DoubleBytes(String input) {
		if (input == null || input.length() <= 0) {
			return "";
		}
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 转换全角至半角字符
	 * 
	 * 全角空格为12288，半角空格为32
	 * 
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @param source
	 *            需要转换的源字符串
	 * @return
	 */
	public static final String transferDouble2SingleBytes(String source) {
		if (source == null || source.length() <= 0) {
			return "";
		}
		// 全角至半角
		char[] c = source.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		List lstContent = new ArrayList(5);
		lstContent.add("交易流水号：");
		lstContent.add("交易日期：");
		lstContent.add("交易时间：");
		appendData2List(lstContent, 0, "123456789");
		appendData2List(lstContent, 1, "2009-03-24");
		appendData2List(lstContent, 2, "14:30:30");

	//	System.out.println(ObjInfo.toStr(lstContent));

		String source = "ABCD1234 5";
		String target = transferSingle2DoubleBytes(source);

		System.out.println(source);
		System.out.println("半角转全角:" + target);
		System.out.println("全角转半角:"
				+ transferDouble2SingleBytes(target));
	}
	

}
