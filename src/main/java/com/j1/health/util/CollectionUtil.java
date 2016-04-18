package com.j1.health.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * 集合工具类
 * @author 辉
 *
 */
public class CollectionUtil {
	
	private static transient Set<Map<String,Object>> set = new HashSet<>();
	
	private static transient List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
	/**
	 * 存放队列副本
	 * @param queue
	 * @return set
	 */
	public static synchronized void copyFromQueue(BlockingQueue<Map<String,Object>> queue){
		if(null != queue && queue.size() > 0){
			set.addAll(queue);
		}
	}
	
	/**
	 * 获取副本
	 * @return
	 */
	public static synchronized Set<Map<String,Object>> getDuplicateQueue(){
		return set;
	}
	
	/**
	 * 把结果集放到list
	 * @param map
	 */
	public static synchronized void putStringToList(Map<String,Object> map){
		list.add(map);
	}
	
	/**
	 * 获取结果集
	 * @return
	 */
	public static synchronized List<Map<String,Object>> getList(){
		return list;
	}
	
}
