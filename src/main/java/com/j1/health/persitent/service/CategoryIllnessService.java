package com.j1.health.persitent.service;

import java.util.List;
import java.util.Map;

import com.j1.health.persitent.service.base.IBaseService;


public interface CategoryIllnessService extends IBaseService{
	
	public int insertCategoryAndIllnessByBatch(Map<String,Object> category,List<Map<String,Object>> illness)throws Exception;

}
