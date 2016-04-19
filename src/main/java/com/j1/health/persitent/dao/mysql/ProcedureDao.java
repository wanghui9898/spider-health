package com.j1.health.persitent.dao.mysql;

import java.util.Map;

import com.j1.health.persitent.dao.base.MyBatisRepository;

@MyBatisRepository("procedure")
public interface ProcedureDao {
	
	//动态调用存储过程
	public int addDynamicProcedure(Map<String,Object> param);
	
}
