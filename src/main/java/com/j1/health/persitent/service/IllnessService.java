package com.j1.health.persitent.service;

import java.util.List;
import java.util.Map;

import com.j1.health.persitent.mysql.service.base.IBaseService;


public interface IllnessService extends IBaseService{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);

}
