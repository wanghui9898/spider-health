package com.j1.health.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 配置文件
 * @author 王辉
 *
 */
public class SpringContextUtil {
	
	private static ApplicationContext applicationContext;
	
	static{
		applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/applicationContext*.xml"});
	}
	
	/*public static ApplicationContext getApplicationContext(){
		if(null == applicationContext){
			applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/applicationContext*.xml"});
		}
		return applicationContext;
	}*/
	
	/**
	 * 根据
	 * @param beanName
	 * @return
	 */
	public static Object getBeanbyName(String beanName){
		return applicationContext.getBean(beanName);
	}

}
