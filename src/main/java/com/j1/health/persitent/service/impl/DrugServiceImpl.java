package com.j1.health.persitent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.dao.mysql.DrugDao;
import com.j1.health.persitent.service.DrugService;

@Service("drugService")
public class DrugServiceImpl implements DrugService{
	
	@Autowired
	private DrugDao drugDao;
	
	public List<Map<String, Object>> getDrugList(Map<String, Object> param) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map =  this.drugDao.getDrugList(param);
		if(null != illness_Map && illness_Map.size() > 0){
			for(Map<String, Object> map:illness_Map){
				Object url = map.get("illnessUrl");
				Object sid = map.get("sid");
				Map<String, Object> rMap = new HashMap<>();
				if(null != url && sid != null){
					rMap.put(url.toString(), sid);
					resultList.add(rMap);
				}
			}
		}
		return resultList;
	}

	@Override
	public int insertDrugByBatch(List<Map<String, Object>> lists) {
		return this.drugDao.insertDrugByBatch(lists);
	}

}
