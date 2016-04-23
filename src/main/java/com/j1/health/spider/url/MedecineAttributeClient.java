package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j1.health.persitent.service.IllnessService;
import com.j1.health.spider.process.illness.IllnessAttributeProcess;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.SpiderQueue;
import com.j1.health.util.SpiderThread;
import com.j1.health.util.SpringContextUtil;

/**
 * 获取疾病属性url
 * @author 王辉
 *
 */
public class MedecineAttributeClient {
	
	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		IllnessAttributeProcess process = (IllnessAttributeProcess) SpringContextUtil.getBeanbyName("illnessAttributeProcess");
		//使用线程的方式执行爬虫job
		IllnessService illnessService = (IllnessService) SpringContextUtil.getBeanbyName("illnessService");
		List<Map<String,Object>> list = illnessService.getIllnessToAttributeUrl();
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
		System.out.println("已经爬取的数据为小为:"+resultList.size());
		if(null != resultList && resultList.size() > 0){
			long dbStartTime = System.currentTimeMillis();
			int result_code = illnessService.insertIllnessToAttributeUrlByBatch(resultList);
			long dbEndTime = System.currentTimeMillis();
			if(result_code > 0){
				System.out.println("插入成功"+"---"+"花费了"+(dbEndTime-dbStartTime));
			}else{
				System.out.println("插入失败!");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
		//补充插入失败的url
	}

}
