package com.j1.health.client;

import com.j1.health.process.category.CategoryProcessor;
import com.j1.health.util.SpringContextUtil;

import us.codecraft.webmagic.Spider;

/**
 * 抓取第一类目
 * @author 王辉
 *
 */
public class CategoryClient {
	
	public static void main(String[] args) {
		CategoryProcessor categoryProcessor = (CategoryProcessor) SpringContextUtil.getBeanbyName("categoryProcessor");
		String url = "http://ypk.39.net/";
		Spider.create(categoryProcessor).addUrl(url).run();
	}
	
}
