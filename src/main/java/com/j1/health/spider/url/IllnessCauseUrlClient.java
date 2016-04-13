package com.j1.health.spider.url;

import com.j1.health.spider.process.DrugUrlProcessor;

import us.codecraft.webmagic.Spider;

/**
 * 抓取疾病下面对应的url
 * @author 王辉
 *
 */
public class IllnessCauseUrlClient {
	
	public static void main(String[] args) {
		String url = "http://yao.xywy.com/jibing/248.htm";
		Spider.create(new DrugUrlProcessor())
		.addUrl(url)
		.run();
	}
	
}
