package com.j1.health.dao;

import java.util.List;
import java.util.Map;

import com.j1.health.dao.base.CrudDao;
import com.j1.health.dao.base.MyBatisRepository;

@MyBatisRepository("illness")
public interface IllnessDao extends CrudDao{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);

}
