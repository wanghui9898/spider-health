package com.j1.health.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 爬虫队列
 * @author 王辉
 *
 */
public class SpiderQueue {
	
	private static transient BlockingQueue<Map<String,Object>> queue = new LinkedBlockingQueue<Map<String,Object>>();
	
	public synchronized static void addTask(List<Map<String,Object>> map){
		for(Map<String,Object> task:map){
			queue.add(task);
		}
	}
	
	public synchronized static BlockingQueue<Map<String,Object>> getQueue(){
		return queue;
	}

}
