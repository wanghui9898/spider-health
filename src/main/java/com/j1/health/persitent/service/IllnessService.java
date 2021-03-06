package com.j1.health.persitent.service;

import java.util.List;
import java.util.Map;


public interface IllnessService{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);
	
	public List<Map<String,Object>> getIllnessList();//根据疾病的url找到对应
	
	public List<Map<String,Object>> getErrorIllnessList();//获取没有爬虫下来的url
	
	public List<Map<String,Object>> getIllnessMedecineList();//获取疾病对应的商品url
	
	public List<Map<String,Object>> getMedecineList(Map<String,Object> param);//获取药品库,分页获取
	
	public List<Map<String,Object>> getIllnessToAttributeUrl();//获取疾病明细,数量不多
	
	public int insertIllnessCauseByBatch(List<Map<String,Object>> param);
	
	public int insertIllnessMedecineByBatch(List<Map<String,Object>> param);//插入疾病和医药url
	
	public int insertMedecineByBatch(List<Map<String,Object>> param);
	
	public int insertIllnessToAttributeUrlByBatch(List<Map<String,Object>> param);

}
