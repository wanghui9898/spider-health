package com.j1.health.persitent.dao.mysql;

import java.util.List;
import java.util.Map;

import com.j1.health.persitent.dao.base.CrudDao;
import com.j1.health.persitent.dao.base.MyBatisRepository;

@MyBatisRepository("illness")
public interface IllnessDao extends CrudDao{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);

}
