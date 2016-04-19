package com.j1.health.persitent.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.dao.mysql.ProcedureDao;
import com.j1.health.persitent.service.ProcedureService;

@Service("procedureService")
public class ProcedureServiceImpl implements ProcedureService{
	
	@Autowired
	private ProcedureDao procedureDao;
	
	/**
	 * 动态接收存储过程
	 */
	public int addDynamicProcedure(Map<String, Object> param) {
		return this.procedureDao.addDynamicProcedure(param);
	}

}
