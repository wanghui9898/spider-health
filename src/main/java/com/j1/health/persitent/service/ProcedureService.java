package com.j1.health.persitent.service;

import java.util.Map;

/**
 * 存储过程
 * @author 王辉
 *
 */
public interface ProcedureService {
	
	//动态调用存储过程
	public int addDynamicProcedure(Map<String,Object> param);

}
