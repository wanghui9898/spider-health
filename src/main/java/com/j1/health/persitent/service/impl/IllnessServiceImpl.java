package com.j1.health.persitent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.dao.mysql.IllnessDao;
import com.j1.health.persitent.service.IllnessService;

@Service("illnessService")
public class IllnessServiceImpl implements IllnessService{
	
	@Autowired
	private IllnessDao illnessDao;

	public int insertIllnessByBatch(List<Map<String,Object>> list){
		return this.illnessDao.insertIllnessByBatch(list);
	}
	
	/**
	 * 获取疾病url
	 */
	public List<Map<String, Object>> getIllnessList() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map = this.illnessDao.getIllnessList();
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
	
	//根据
	public int insertIllnessCauseByBatch(List<Map<String, Object>> param) {
		return this.illnessDao.insertIllnessCauseByBatch(param);
	}
	
	/**
	 * 获取未跑完的f_illness
	 */
	public List<Map<String, Object>> getErrorIllnessList() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map = this.illnessDao.getErrorIllnessList();
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
	public List<Map<String, Object>> getIllnessMedecineList() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map = this.illnessDao.getIllnessMedecineList();
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
	
	/**
	 * 插入疾病和药品库url
	 */
	public int insertIllnessMedecineByBatch(List<Map<String, Object>> param) {
		return this.illnessDao.insertIllnessMedecineByBatch(param);
	}
	
	/**
	 * 获取药品库
	 */
	public List<Map<String, Object>> getMedecineList(Map<String,Object> param) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map = this.illnessDao.getMedecineList(param);
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
	
	/**
	 * 批量插入药品库
	 */
	public int insertMedecineByBatch(List<Map<String, Object>> param) {
		return this.illnessDao.insertMedecineByBatch(param);
	}
	
	/**
	 * 获取疾病明细
	 */
	public List<Map<String, Object>> getIllnessToAttributeUrl() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> illness_Map = this.illnessDao.getIllnessToAttributeUrl();
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
	
	//批量插入属性
	public int insertIllnessToAttributeUrlByBatch(List<Map<String, Object>> param) {
		return this.illnessDao.insertIllnessToAttributeUrlByBatch(param);
	}

}
