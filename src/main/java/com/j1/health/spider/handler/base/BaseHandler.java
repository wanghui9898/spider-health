package com.j1.health.spider.handler.base;

import us.codecraft.webmagic.Page;

/**
 * 封装一些基本处理page的方法
 * @author 辉
 *
 */
public interface BaseHandler {
	
	public void dealDefaultPage(Page page);
	
	public Page dealPage(Page page);

}
