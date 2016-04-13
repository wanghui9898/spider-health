package com.j1.health.persitent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.dao.IllnessDao;
import com.j1.health.service.IllnessService;

@Service("illnessService")
public class IllnessServiceImpl implements IllnessService{
	
	@Autowired
	private IllnessDao illnessDao;

	@Override
	public Object get(Object id) {
		return null;
	}

	@Override
	public int saveOrUpdate(Object entity) {
		return 0;
	}

	@Override
	public int delete(Object id) {
		return 0;
	}

	@Override
	public List getAll(Map reqParam) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertIllnessByBatch(List<Map<String,Object>> list){
		return this.illnessDao.insertIllnessByBatch(list);
	}

}
