package com.ring.front.util;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @category 配置文件读取工具
 * @author zhangguohua
 * @since 2014-11-21
 */
@SuppressWarnings("serial")
public class PropertiesConfigUtil extends HttpServlet {
	static Log logger = LogFactory.getLog(PropertiesConfigUtil.class);
    private static ResourceBundle bundle = null;

    /**
     * @category加载properties文件
     */
    public void init() {           
    	System.out.println("开始加载Properties文件.....");
		String file = getInitParameter("properties-init-file");  
		if(file != null){
			if(bundle == null){
				bundle = ResourceBundle.getBundle(file);
			}
		} else {
			System.out.println("该Properties文件不存在："+file);
		}
		System.out.println("加载Properties文件完成.....");
	}     
    
	/**
     * 读取配置文件中的值：*.properties
     * @param key
     * @return String
     */
    public static String getProperty(String key){
    	String value = bundle.getString(key);
        if(value==null || "".equals(value)) {
        	value = "";
        	logger.info("不存在的配置项：" + key);
        }
        return value;
    }
}
