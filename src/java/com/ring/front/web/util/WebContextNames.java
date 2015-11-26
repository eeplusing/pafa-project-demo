package com.ring.front.web.util;

import com.paic.pafa.appclient.WebPafaAcClientResolver;

/**
 * 定义web层的Context对象名称。它定义了web层用到的对象的bean id，目的是减少在
 * 代码中对bean名称的直接引用。
 * <p/>
 * Context对象在<code>web-context.xml</code>中定义
 *
 * @author PAFA3 Template
 */
public class WebContextNames {
    /**
     * PAFA AC的名称，在common-context.xml中定义
     */
    public static final String PAFA_AC = WebPafaAcClientResolver.getInstance().getDefaultPafaAcClientBean().getAcName();
}
