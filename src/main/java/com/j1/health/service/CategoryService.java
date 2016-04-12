package com.j1.health.service;

import java.util.Map;

import com.j1.health.service.common.IBaseService;

/**
 * 类目服务
 * @author 王辉
 *
 */
public interface CategoryService extends IBaseService{
	
	public int insertCategoryByMap(Map<String,Object> param);

}
