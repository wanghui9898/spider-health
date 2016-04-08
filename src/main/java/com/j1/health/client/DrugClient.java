package com.j1.health.client;

import com.j1.health.process.DrugUrlProcessor;

import us.codecraft.webmagic.Spider;

/**
 * 爬取url
 * @author 辉
 *
 */
public class DrugClient {
	
	
	public static void main(String[] args) {
		String url = "aa.com";
//		String url = "aa.com";
		Spider.create(new DrugUrlProcessor(url))
		.addUrl(url)
		.run();
	}


}
