package com.j1.health.persitent.dao.mysql;

import java.util.Map;

import com.j1.health.persitent.dao.base.CrudDao;
import com.j1.health.persitent.dao.base.MyBatisRepository;

@MyBatisRepository
public interface CategoryDao extends CrudDao{
	
	public int insertCategoryByMap(Map<String,Object> param);

}
