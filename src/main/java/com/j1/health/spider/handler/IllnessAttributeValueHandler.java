package com.j1.health.spider.handler;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.j1.health.util.CollectionUtil;
import com.j1.health.util.ConsField;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

@Service("illnessAttributeValueHandler")
public class IllnessAttributeValueHandler {
	
	public void process(Page page,String type) {
		if(ConsField.CAUSE.equals(type)){
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessCauseName");
		}
		if(ConsField.PREVENT.equals(type)){
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessProtectName");
		}
		if(ConsField.neopathy.equals(type)){//并发症
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessNeopathyName");
		}
		if(ConsField.symptom.equals(type)){//并发症
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessSymptomName");
		}if(ConsField.inspect.equals(type)){//并发症
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessInspectName");
		}
		if(ConsField.diagnosis.equals(type)){//诊断
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessDiagnosisName");
		}
		if(ConsField.treat.equals(type)){//治疗
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessTreatName");
		}
		if(ConsField.nursing.equals(type)){//护理
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessNurseName");
		}
		if(ConsField.food.equals(type)){//饮食保健
			Selectable select = getSelectPage(page);
			pushToQueue(page,select,"illnessFoodName");
		}
	}
	
	private Selectable getSelectPage(Page page){
		Selectable select = page.getHtml().xpath("//div[@class='jib-janj bor clearfix']/div[2]/html()");
//		System.out.println(select.toString());
		return select;
	}
	
	private void pushToQueue(Page page,Selectable select,String colName){
		Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
		String url = page.getRequest().getUrl();
		Map<String,Object> illness_map = Maps.newLinkedHashMap();
		for(Map<String,Object> map:copyQueue){
			if(map.containsKey(url)){
				illness_map.put("illnessId", map.get(url));
				illness_map.put(colName, select.toString());
				CollectionUtil.putStringToList(illness_map);
			}
		}
	}
}
