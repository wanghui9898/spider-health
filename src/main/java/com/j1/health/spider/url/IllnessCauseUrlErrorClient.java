package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j1.health.persitent.service.IllnessService;
import com.j1.health.spider.process.illness.IllnessCauseUrlProcess;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.SpiderQueue;
import com.j1.health.util.SpiderThread;
import com.j1.health.util.SpringContextUtil;

/**
 * 抓取疾病下面对应的url
 * @author 王辉
 *
 */
public class IllnessCauseUrlErrorClient {
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		IllnessCauseUrlProcess process = (IllnessCauseUrlProcess) SpringContextUtil.getBeanbyName("illnessCauseUrlProcess");
		//使用线程的方式执行爬虫job
		IllnessService illnessService = (IllnessService) SpringContextUtil.getBeanbyName("illnessService");
//		List<Map<String,Object>> list = illnessService.getErrorIllnessList();
		List<Map<String,Object>> list = Lists.newArrayList();
		Map<String,Object>  map = Maps.newLinkedHashMap();
		map.put("http://yao.xywy.com/jibing/904.htm", 5);
		list.add(map);
		SpiderQueue.addTask(list);
		CollectionUtil.copyFromQueue(SpiderQueue.getQueue());
		SpiderThread[] sp = new SpiderThread[1];
		for(int i=0;i<sp.length;i++){
			SpiderThread spiderThread = new SpiderThread("",null,process);
			Thread thread = new Thread(spiderThread);
			thread.start();
			thread.join();
		}
		List<Map<String,Object>> resultList = CollectionUtil.getList();//做持久化
		System.out.println("大小"+resultList.size());
		if(null != resultList && resultList.size() > 0){
			int result = illnessService.insertIllnessCauseByBatch(resultList);
			if(result > 0){
				System.out.println("batch insert success!");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
		List<Map<String,Object>> error_List = CollectionUtil.getErrorList();
		for(Map<String,Object> mlist:error_List){
			System.out.println(mlist);
		}
		//继续过滤重复数据
	}
	
}
