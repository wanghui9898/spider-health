package com.j1.health.process.base;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 简单的页面处理流程
 * @author 辉
 *
 */
public abstract class BasePageProcess implements PageProcessor{
	
	private Site site = Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko)"
			+ "Chrome/31.0.1650.57 Safari/537.36").setSleepTime(100);
	
	public Site getSite() {
		return site;
	}

}
