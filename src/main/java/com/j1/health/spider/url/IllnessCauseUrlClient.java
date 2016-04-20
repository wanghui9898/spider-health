package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.j1.health.persitent.service.IllnessService;
import com.j1.health.persitent.service.ProcedureService;
import com.j1.health.spider.process.illness.IllnessCauseUrlProcess;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.ConsField;
import com.j1.health.util.ProcedureUtil;
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
		int result = illnessService.insertIllnessCauseByBatch(resultList);
		if(result > 0){
			System.out.println("batch insert success!");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
		//抓取没有抓取的任务
		Map<String,Object> param = Maps.newHashMap();
		param.put(ConsField.PROCEDURE_NAME, "p_f_illness_error");
		int resultCode = ProcedureUtil.callProcedure(param);
		if(resultCode > 0){
			System.out.println("调用存储过程成功!");
		}
	}
	
}
