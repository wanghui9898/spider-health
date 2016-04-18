package com.j1.health.persitent.dao.mysql;

import com.j1.health.persitent.dao.base.CrudDao;
import com.j1.health.persitent.dao.base.MyBatisRepository;

@MyBatisRepository("pIllness")
public interface PIllnessDao extends CrudDao{
	
	public int deleteDuplicate();

}
