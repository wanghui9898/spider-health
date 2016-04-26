package com.j1.health.spider.handler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.j1.health.util.CollectionUtil;

import us.codecraft.webmagic.Page;

@Service("illnessAttributeHandler")
public class IllnessAttributeHandler {
	
	
	//获取得到药品url
	public void process(Page page){
		String illness_cause_url = "";//1.病因url
		String illness_protect_url = "";//2.预防url
		String illness_concurrence_url = "";//3.并发url
		String illness_symptom_url = "";//4.症状url
		String illness_check_url = "";//5.检查url
		String illness_identify_url = "";//6.诊断url
		String illness_treatment_url = "";//7.治疗url
		String illness_nurse_url = "";//8.护理url
		String illness_maintenance_url = "";//9.日常保健url
		List<String> urls = page.getHtml().xpath("//div[@class='jib-nav fl bor']/div").links().all();
		if(null != urls && urls.size() > 0){
			for(String url:urls){
				if(url.contains("cause")){//1.病因
					illness_cause_url = url;
				}else if(url.contains("prevent")){//2.预防
					illness_protect_url = url;
				}else if(url.contains("neopathy")){//3.并发症
					illness_concurrence_url = url;
				}else if(url.contains("symptom")){//4.症状
					illness_symptom_url = url;
				}else if(url.contains("inspect")){//5.检查
					illness_check_url = url;
				}else if(url.contains("diagnosis")){//6.诊断鉴别
					illness_identify_url = url;
				}else if(url.contains("treat")){//7.治疗
					illness_treatment_url = url;
				}else if(url.contains("nursing")){//8.护理
					illness_nurse_url = url;
				}else if(url.contains("food")){//9.饮食保健
					illness_maintenance_url = url;
				}
			}
		}
		Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
		String url = page.getRequest().getUrl();
		Map<String,Object> illness_map = Maps.newLinkedHashMap();
		for(Map<String,Object> map:copyQueue){
			if(map.containsKey(url)){
				illness_map.put("illnessId", map.get(url));
				illness_map.put("illnessCauseUrl", illness_cause_url);
				illness_map.put("illnessProtectUrl", illness_protect_url);
				illness_map.put("illnessConcurrenceUrl", illness_concurrence_url);
				illness_map.put("illnessSymptomUrl", illness_symptom_url);
				illness_map.put("illnessCheckUrl", illness_check_url);
				illness_map.put("illnessIdentifyUrl", illness_identify_url);
				illness_map.put("illnessTreatmentUrl", illness_treatment_url);
				illness_map.put("illnessNurseUrl", illness_nurse_url);
				illness_map.put("illnessMaintenanceUrl", illness_maintenance_url);
				CollectionUtil.putStringToList(illness_map);
			}
		}
		
	}
	
}
