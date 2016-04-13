package com.j1.health.spider.url;


import com.j1.health.spider.process.DrugUrlProcessor;

import us.codecraft.webmagic.Spider;

/**
 * 爬取url
 * @author 辉
 *
 */
public class DrugClient {
	
	
	public static void main(String[] args) {
		String url = "http://yao.xywy.com/jibing/248.htm";
		Spider.create(new DrugUrlProcessor())
		.addUrl(url)
		.run();
	}


}
