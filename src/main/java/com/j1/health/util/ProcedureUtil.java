package com.j1.health.util;

import java.util.Map;

import com.j1.health.persitent.service.ProcedureService;

public class ProcedureUtil {

	public static int callProcedure(Map<String,Object> param){
		ProcedureService procedureService = (ProcedureService) SpringContextUtil.getBeanbyName("procedureService");
		int result = procedureService.addDynamicProcedure(param);
		return result;
	}
}
