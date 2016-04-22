package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.j1.health.persitent.service.IllnessService;
import com.j1.health.spider.process.medecine.MedecineProcessor;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.SpiderQueue;
import com.j1.health.util.SpiderThread;
import com.j1.health.util.SpringContextUtil;

/**
 * 获取药品库
 * @author 王辉
 *
 */
public class MedecineUrlClient {
	
	public static void main(String[] args) throws Exception{
		if(null == args || args.length == 0 || args.length !=2){
			System.err.println("param error!");
			System.exit(-1);
		}
		long startTime = System.currentTimeMillis();
		MedecineProcessor process = (MedecineProcessor) SpringContextUtil.getBeanbyName("medecineProcessor");
		//使用线程的方式执行爬虫job
		IllnessService illnessService = (IllnessService) SpringContextUtil.getBeanbyName("illnessService");
		Map<String,Object> pageMap = Maps.newHashMap();
		pageMap.put("start", Integer.parseInt(args[0]));
		pageMap.put("pageSize", Integer.parseInt(args[1]));
		List<Map<String,Object>> list = illnessService.getMedecineList(pageMap);
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
			int result_code = illnessService.insertMedecineByBatch(resultList);
			long dbEndTime = System.currentTimeMillis();
			if(result_code > 0){
				System.out.println("插入成功"+"---"+"花费了"+(dbEndTime-dbStartTime));
			}else{
				System.out.println("插入失败!");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
		//做差集把没有抓取的url放到url
		/*Map<String,Object> procedure_map = Maps.newLinkedHashMap();
		procedure_map.put(ConsField.PROCEDURE_NAME, "p_f_illness_medecine_spider_url_error");
		int resultCode = ProcedureUtil.callProcedure(procedure_map);
		if(resultCode > 0){
			System.out.println("调用存储过程成功!");
		}*/
	}
	
}
