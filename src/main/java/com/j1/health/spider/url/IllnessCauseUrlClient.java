package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

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
public class IllnessCauseUrlClient {
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		IllnessCauseUrlProcess process = (IllnessCauseUrlProcess) SpringContextUtil.getBeanbyName("illnessCauseUrlProcess");
		//使用线程的方式执行爬虫job
		IllnessService illnessService = (IllnessService) SpringContextUtil.getBeanbyName("illnessService");
		List<Map<String,Object>> list = illnessService.getIllnessList();
		/*List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> m = new HashMap<>();
		m.put("http://yao.xywy.com/jibing/120.htm", 227);
		list.add(m);*/
		SpiderQueue.addTask(list);
		CollectionUtil.copyFromQueue(SpiderQueue.getQueue());
		SpiderThread[] sp = new SpiderThread[10];
		for(int i=0;i<sp.length;i++){
			SpiderThread spiderThread = new SpiderThread("",null,process);
			Thread thread = new Thread(spiderThread);
			thread.start();
			thread.join();
		}
		List<Map<String,Object>> resultList = CollectionUtil.getList();//做持久化
//		System.out.println(resultList.size());
		int result = illnessService.insertIllnessCauseByBatch(resultList);
		if(result > 0){
			System.out.println("batch insert success!");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
	}
	
}
