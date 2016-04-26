package com.j1.health.persitent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.dao.mysql.IllnessAttributeDao;
import com.j1.health.persitent.service.attr.IllnessAttributeService;

@Service("illnessAttributeService")
public class IllnessAttributeServiceImpl implements IllnessAttributeService{
	
	@Autowired
	private IllnessAttributeDao illnessAttributeDao;

	@Override
	public List<Map<String, Object>> getDynamicInfo(Map<String, Object> param) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map =  this.illnessAttributeDao.getDynamicInfo(param);
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
	public int insertDynamicInfo(Map<String,Object> param) {
		return this.illnessAttributeDao.insertDynamicInfo(param);
	}

	@Override
	public int insertIllnessCause(List<Map<String, Object>> param) {
		return illnessAttributeDao.insertIllnessCause(param);
	}

	@Override
	public int insertIllnessPrevent(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessPrevent(param);
	}

	@Override
	public int insertIllnessnNeopathy(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnNeopathy(param);
	}

	@Override
	public int insertIllnessnSymptom(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnSymptom(param);
	}

	@Override
	public int insertIllnessnCheck(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnCheck(param);
	}

	@Override
	public int insertIllnessnDiagnosis(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnDiagnosis(param);
	}

	@Override
	public int insertIllnessnTreat(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnTreat(param);
	}

	@Override
	public int insertIllnessnNurse(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnNurse(param);
	}

	@Override
	public int insertIllnessnFood(List<Map<String, Object>> param) {
		return this.illnessAttributeDao.insertIllnessnFood(param);
	}

}
