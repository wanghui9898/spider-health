package com.j1.health.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 利用线程处理爬虫
 * @author 王辉
 *
 */
public class SpiderThread implements Runnable{
	
	protected Logger logger = LoggerFactory.getLogger(getClass()); 
	
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
							try{
								Spider spider = Spider.create(pageProcessor).addUrl(url);
								Thread executorThread = new Thread(spider);
								executorThread.start();
								executorThread.join();
							}catch (Exception e){
								e.printStackTrace();
								logger.error("%C{1}", url + "抓取失败!");
								pushErrorUrlToQueue(url,map);
							}
						}else if(null != pipeline){
							Spider spider = Spider.create(pageProcessor).addUrl(url).addPipeline(pipeline);
							Thread executorThread = new Thread(spider);
							executorThread.start();
							executorThread.join();
						}
					}
				}
			} catch (InterruptedException e) {
				logger.error("%C{1}", e.getMessage());
			}
		}
	}
	
	
	/**
	 * 如果失败的话放入到失败队列中
	 * @param url
	 * @param map
	 */
	private void pushErrorUrlToQueue(String url,Map<String,Object> map){
		Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
		Map<String,Object> resultMap = new LinkedHashMap<>();
		resultMap.put("illnessId", map.get(url));
		resultMap.put("illnessCauseUrl", url);
		resultMap.put("version", "1");//以后做活
		CollectionUtil.putErrorQueue(resultMap);
	}
	
}


