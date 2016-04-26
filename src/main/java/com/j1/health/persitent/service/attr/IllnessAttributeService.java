package com.j1.health.persitent.service.attr;

import java.util.List;
import java.util.Map;

/**
 * 获取疾病属性
 * @author 王辉
 *
 */
public interface IllnessAttributeService {
	
	public List<Map<String,Object>> getDynamicInfo(Map<String,Object> param);
	
	public int insertDynamicInfo(Map<String,Object> param);//动态插入表结构
	
	public int insertIllnessCause(List<Map<String,Object>> param);//批量插入病因
	
	public int insertIllnessPrevent(List<Map<String,Object>> param);//批量插入预防
	
	public int insertIllnessnNeopathy(List<Map<String,Object>> param);//批量插入并发症
	
	public int insertIllnessnSymptom(List<Map<String,Object>> param);//批量插入症状
	
	public int insertIllnessnCheck(List<Map<String,Object>> param);//批量插入检查
	
	public int insertIllnessnDiagnosis(List<Map<String,Object>> param);//批量插入诊断
	
	public int insertIllnessnTreat(List<Map<String,Object>> param);//批量插入治疗
	
	public int insertIllnessnNurse(List<Map<String,Object>> param);//批量插入护理
	
	public int insertIllnessnFood(List<Map<String,Object>> param);//批量插入饮食保健
	
}
