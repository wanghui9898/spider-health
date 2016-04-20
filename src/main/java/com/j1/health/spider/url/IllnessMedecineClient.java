package com.j1.health.spider.url;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j1.health.persitent.service.IllnessService;
import com.j1.health.spider.process.illness.IllnessMedecineProcess;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.ConsField;
import com.j1.health.util.ProcedureUtil;
import com.j1.health.util.SpiderQueue;
import com.j1.health.util.SpiderThread;
import com.j1.health.util.SpringContextUtil;

/**
 * 抓取疾病对应的药品库
 * @author 王辉
 *
 */
public class IllnessMedecineClient {
	
	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		IllnessMedecineProcess process = (IllnessMedecineProcess) SpringContextUtil.getBeanbyName("illnessMedecineProcess");
		//使用线程的方式执行爬虫job
		IllnessService illnessService = (IllnessService) SpringContextUtil.getBeanbyName("illnessService");
		List<Map<String,Object>> list = illnessService.getIllnessMedecineList();
		/*List<Map<String,Object>> list = Lists.newArrayList();
		Map<String,Object> map = Maps.newHashMap();
		map.put("http://yao.xywy.com/jibing/248-1.htm", 1);
		list.add(map);*/
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
		System.out.println("队列大小:"+resultList.size());
		List<Map<String,Object>> new_resultList = Lists.newArrayList();
		for(Map<String,Object> illMap:resultList){
			if(illMap.containsKey("count")){
				Object count = illMap.get("count");//获取数量
				Object illnessId = illMap.get("illnessId");//获取疾病id
				Object illnessMedecineUrl = illMap.get("illnessMedecineUrl");//获取url
				String spiderUrl = "";
				if(null != illnessMedecineUrl){
					spiderUrl = illnessMedecineUrl.toString();
				}
				if(null != count){
					int size = Integer.parseInt(count.toString());
					for(int i=1;i<=size;i++){
						spiderUrl = spiderUrl.substring(0,spiderUrl.indexOf("-")+1);
						spiderUrl = spiderUrl + i + ".htm";
						Map<String,Object> new_map = Maps.newLinkedHashMap();
						new_map.put("illnessId", illnessId);
						new_map.put("illnessMedecineUrl", spiderUrl);
						new_resultList.add(new_map);
					}
				}
			}
		}
		if(null != new_resultList && new_resultList.size() > 0){
			int result_Code = illnessService.insertIllnessMedecineByBatch(new_resultList);
			if(result_Code > 0){
				System.out.println("插入成功!");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
		//做差集把没有抓取的url放到url
		Map<String,Object> procedure_map = Maps.newLinkedHashMap();
		procedure_map.put(ConsField.PROCEDURE_NAME, "p_f_illness_medecine_spider_url");
		int resultCode = ProcedureUtil.callProcedure(procedure_map);
		if(resultCode > 0){
			System.out.println("调用存储过程成功!");
		}
	}
	
}
