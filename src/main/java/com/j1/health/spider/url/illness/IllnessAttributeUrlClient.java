package com.j1.health.spider.url.illness;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.j1.health.persitent.service.attr.IllnessAttributeService;
import com.j1.health.spider.process.illness.IllnessAttributeValueProcess;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.ConsField;
import com.j1.health.util.SpiderQueue;
import com.j1.health.util.SpiderThread;
import com.j1.health.util.SpringContextUtil;

/**
 * 获取属性
 * @author 王辉
 *
 */
public class IllnessAttributeUrlClient {

	public static void main(String[] args)throws Exception {
		long startTime = System.currentTimeMillis();
		IllnessAttributeValueProcess process = (IllnessAttributeValueProcess) SpringContextUtil.getBeanbyName("illnessAttributeValueProcess");
		process.setType(ConsField.food);
		//使用线程的方式执行爬虫job
		IllnessAttributeService illnessAttributeService = (IllnessAttributeService) SpringContextUtil.getBeanbyName("illnessAttributeService");
		Map<String,Object> param = Maps.newLinkedHashMap();
		param.put("colName", "illness_maintenance_url");
		param.put("tableName", "f_illness_attribute_url");
		List<Map<String,Object>> list = illnessAttributeService.getDynamicInfo(param);
//		List<Map<String,Object>> list = Lists.newArrayList();
//		param.put("http://jib.xywy.com/il_sii/cause/3697.htm", 1);
//		list.add(param);
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
			int result_code = 0;
			if(ConsField.CAUSE.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessCause(resultList);
			}else if(ConsField.PREVENT.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessPrevent(resultList);
			}else if(ConsField.neopathy.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnNeopathy(resultList);
			}else if(ConsField.symptom.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnSymptom(resultList);
			}else if(ConsField.inspect.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnCheck(resultList);
			}else if(ConsField.diagnosis.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnDiagnosis(resultList);
			}else if(ConsField.treat.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnTreat(resultList);
			}else if(ConsField.nursing.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnNurse(resultList);
			}else if(ConsField.food.equals(process.getType())){
				result_code = illnessAttributeService.insertIllnessnFood(resultList);
			}
			long dbEndTime = System.currentTimeMillis();
			if(result_code > 0){
				System.out.println("插入成功"+"---"+"花费了"+(dbEndTime-dbStartTime));
			}else{
				System.out.println("插入失败!");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spend Total Time = "+(endTime-startTime));
	}
	
}
