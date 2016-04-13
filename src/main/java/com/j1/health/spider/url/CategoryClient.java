package com.j1.health.spider.url;

import com.j1.health.pipeline.IllnessPipeline;
import com.j1.health.process.category.CategoryProcessor;
import com.j1.health.util.SpringContextUtil;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 抓取第一类目
 * @author 王辉
 *
 */
public class CategoryClient {
	
	public static void main(String[] args) {
		CategoryProcessor categoryProcessor = (CategoryProcessor) SpringContextUtil.getBeanbyName("categoryProcessor");
		Pipeline consolePipeline = (IllnessPipeline) SpringContextUtil.getBeanbyName("illnessPipeline"); 
		String url = "http://yao.xywy.com/jibing/";//获取疾病库
		Spider.create(categoryProcessor)
				.addUrl(url)
				.addPipeline(consolePipeline)
				.run();
	}
	
}
