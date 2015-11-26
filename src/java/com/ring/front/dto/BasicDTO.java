package com.ring.front.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @description
 * @author GANCHUNGEN512
 * @date 2014-8-13
 */
public class BasicDTO {
	public String toString() {
		try {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.MULTI_LINE_STYLE);
		} catch (Throwable e) {
			return getClass().getName();
		}
	}
}
