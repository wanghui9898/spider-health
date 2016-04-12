package com.j1.health.pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.service.CategoryIllnessService;
import com.j1.health.util.ConsField;
import com.j1.health.util.StringUtil;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 控制台打印
 * @author 王辉
 *
 */
@Service("illnessPipeline")
public class IllnessPipeline implements Pipeline{
	
	@Autowired
	private CategoryIllnessService categoryIllnessService;
	
	/***
	 * 处理控制台
	 */
	public void process(ResultItems resultItems, Task task) {
		Map<String, Object> category = resultItems.getAll();
		if(null != category && category.containsKey(ConsField.Illness)){
			Map<String,Object> value =  (Map<String,Object>)category.get(ConsField.Illness);
			if(null != value && value.size() > 0){
				int size = value.size();
				int length = size / 3 ;
				int j = 0;
				for(int i=0;i<length;i++){
					j++;
					String categorys = value.get(ConsField.Category+j).toString();//获取大类
					String urls = value.get(ConsField.URL+j).toString();//获取疾病url
					String titles = value.get(ConsField.Title+j).toString();//获取疾病title
					String[] cgory = StringUtil.convertStringToList(categorys);
					String[] url = StringUtil.convertStringToList(urls);
					String[] title = StringUtil.convertStringToList(titles);
					Map<String,Object> cg = convertStringToMap(cgory);
					List<Map<String,Object>> illness = convertStringToMap(url,title);
					try {
						this.categoryIllnessService.insertCategoryAndIllnessByBatch(cg, illness);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	//拼接疾病
	private List<Map<String,Object>> convertStringToMap(String[] url,String[] title){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(null != url && title != null && url.length == title.length){
			for(int i=0;i<url.length;i++){
				Map<String,Object> map = new LinkedHashMap<>();
				map.put("illneesName", title[i]);
				map.put("illnessUrl", url[i]);
				map.put("version", "1");//以后做活
				list.add(map);
			}
			
		}
		return list;
	}
	
	//拼接疾病大类
	private Map<String,Object> convertStringToMap(String[] category){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != category){
			map.put("bigCategoryName", category[0]);
			map.put("version", "1");//以后做成动态的
		}
		return map;
	}
	
	
}
