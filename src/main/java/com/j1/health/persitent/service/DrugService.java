package com.j1.health.persitent.service;

import java.util.List;
import java.util.Map;

/**
 * 药品服务
 * @author 王辉
 *
 */
public interface DrugService {
	
	public List<Map<String,Object>> getDrugList(Map<String,Object> param);
	
	public int insertDrugByBatch(List<Map<String,Object>> lists);
	
}
