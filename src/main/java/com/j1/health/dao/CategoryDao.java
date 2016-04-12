package com.j1.health.dao;

import java.util.Map;

import com.j1.health.dao.base.CrudDao;
import com.j1.health.dao.base.MyBatisRepository;

@MyBatisRepository
public interface CategoryDao extends CrudDao{
	
	public int insertCategoryByMap(Map<String,Object> param);

}
