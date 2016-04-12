package com.j1.health.service;

import java.util.List;
import java.util.Map;

import com.j1.health.service.common.IBaseService;

public interface IllnessService extends IBaseService{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);

}
