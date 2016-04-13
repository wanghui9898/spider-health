package com.j1.health.persitent.mysql.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.dao.CategoryDao;
import com.j1.health.service.CategoryService;

/**
 * 类目服务
 * @author 王辉
 *
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	public Object get(Object id) {
		return null;
	}

	public int saveOrUpdate(Object entity) {
		return this.categoryDao.insert(entity);
	}

	public int delete(Object id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getAll(Map reqParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCategoryByMap(Map<String, Object> param) {
		return this.categoryDao.insertCategoryByMap(param);
	}

}
