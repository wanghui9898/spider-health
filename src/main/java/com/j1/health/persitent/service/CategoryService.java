package com.j1.health.persitent.service;

import java.util.Map;

import com.j1.health.persitent.mysql.service.base.IBaseService;


/**
 * 类目服务
 * @author 王辉
 *
 */
public interface CategoryService extends IBaseService{
	
	public int insertCategoryByMap(Map<String,Object> param);

}
