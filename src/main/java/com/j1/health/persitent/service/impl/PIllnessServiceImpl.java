package com.j1.health.persitent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.dao.mysql.PIllnessDao;
import com.j1.health.persitent.service.PIllnessService;

@Service("pIllnessService")
public class PIllnessServiceImpl implements PIllnessService{
	
	@Autowired
	private PIllnessDao pIllnessDao;

	//删除疾病
	public int deletePIllness() {
		return this.pIllnessDao.deleteDuplicate();
	}


}
