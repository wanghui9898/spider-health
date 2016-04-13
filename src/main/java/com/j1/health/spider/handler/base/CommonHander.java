package com.j1.health.spider.handler.base;

import us.codecraft.webmagic.Page;

/**
 * 通用的处理类
 * @author 辉
 *
 */
public interface CommonHander {
	
	//处理页面
	public void handlePage(Page page,String url)throws Exception;

}
