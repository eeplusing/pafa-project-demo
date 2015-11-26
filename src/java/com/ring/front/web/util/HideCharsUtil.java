package com.ring.front.web.util;

/**
 * 工具类: 隐去中间的字符
 * 
 * @author luohuan
 * @since 2014/11/12
 */
public class HideCharsUtil {

    /**
     * 获取处理后的字符串
     * 
     * @param sourceStr
     *            原始字串
     * @param rules
     *            (枚举: 用于限定截取规则)
     * @return 处理后的字符串
     */
    public static String getAfterHandlerStr(String sourceStr, RULES rules) {
        StringBuffer sb = new StringBuffer();
        if (null != sourceStr && !"".equals(sourceStr)) {
            switch (rules) {
            case MOBILE:
                sb.append(sourceStr).replace(3, 7, "****");
                break;
            case ID_CARD:
                sb.append(sourceStr).replace(3, 14, "***********");
                break;
            case USER_NAME:
                String starStr = "";
                int starLength = sourceStr.length()>4?4:2;
                for (int i = 0; i < starLength; i++) {
                    starStr= starStr.concat("*");
                }
                if(starLength==2){
                    sb.append(sourceStr).replace(1, sourceStr.length() - 1, starStr);
                }else{
                    sb.append(sourceStr).replace(2, sourceStr.length() - 2, starStr);
                }
                break;

            default:
                sb.append(sourceStr);
            }
        }
        return sb.toString();
    }

    public enum RULES {
        MOBILE, ID_CARD, USER_NAME;
    }
    
   /* public static void main(String[] args) {
        String A = "yueruu";
        String B ="yuerabcdefghidkkef";
        String C ="yuer";
        String sourceStr = A;
        StringBuffer sb = new StringBuffer();
        String starStr = "";
        int starLength = sourceStr.length()>4?4:2;
        for (int i = 0; i < starLength; i++) {
            starStr= starStr.concat("*");
        }
        if(starLength==2){
            sb.append(sourceStr).replace(1, sourceStr.length() - 1, starStr);
        }else{
            sb.append(sourceStr).replace(2, sourceStr.length() - 2, starStr);
        }
    }*/
}