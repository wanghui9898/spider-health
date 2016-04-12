package com.j1.health.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 常用字符串工具类
 * @author 辉
 *
 */
public class StringUtil {
	
	/**
	 * 把字符串转换为list
	 * @param value
	 * @return
	 */
	public static String[] convertStringToList(String value){
		if(null != value && !"".equals(value)){
			value = value.replace("[", "").replace("]", "");
			if(!value.contains(",")){
				String[] return_value = new String[1];
				return_value[0] = value;
				return return_value;
			}
			return StringUtils.split(value, ",");
		}
		return null;
	}
	
	
}
