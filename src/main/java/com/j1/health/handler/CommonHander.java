package com.j1.health.handler;

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
