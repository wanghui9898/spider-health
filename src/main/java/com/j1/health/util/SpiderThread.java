package com.j1.health.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 利用线程处理爬虫
 * @author 王辉
 *
 */
public class SpiderThread implements Runnable{
	
	private String url;//爬取的url
	
	private Pipeline pipeline;//持久化
	
	private PageProcessor pageProcessor;//处理页面
	
	public SpiderThread(String url, Pipeline pipeline,PageProcessor pageProcessor) {
		this.url = url;
		this.pipeline = pipeline;
		this.pageProcessor = pageProcessor;
	}
	
	/**
	 * 多线程爬取页面
	 */
	public void run() {
		boolean flag = true;
		while (flag) {
			BlockingQueue<Map<String,Object>> queue = SpiderQueue.getQueue();//从队列中取数据
			if (queue.size() == 0) {
				flag = false;
				return;
			}
			try {
				Map<String,Object> map = queue.take();
				if(null != map && map.size() == 1){
					Set<String> keySet = map.keySet();
					for(String url:keySet){
						if(null == pipeline){
							Spider spider = Spider.create(pageProcessor).addUrl(url);
							Thread executorThread = new Thread(spider);
							executorThread.start();
							executorThread.join();
							System.out.println("thread is over!");
						}else if(null != pipeline){
							Spider spider = Spider.create(pageProcessor).addUrl(url).addPipeline(pipeline);
							Thread executorThread = new Thread(spider);
							executorThread.start();
							executorThread.join();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


