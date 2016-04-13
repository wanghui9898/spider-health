package com.j1.health.persitent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.persitent.mysql.service.CategoryIllnessService;
import com.j1.health.persitent.mysql.service.CategoryService;
import com.j1.health.persitent.mysql.service.IllnessService;


@Service("categoryIllnessService")
public class CategoryIllnessServiceImpl implements CategoryIllnessService{
	
	@Autowired
	private CategoryService categoryService;//疾病大类
	
	@Autowired
	private IllnessService illnessService;//疾病大类

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
		return null;
	}

	@Override
	public int insertCategoryAndIllnessByBatch(Map<String, Object> category, List<Map<String, Object>> illness)
			throws Exception {
		int result = this.categoryService.insertCategoryByMap(category);//插入大类,返回值表示成功插入的值
		int categoryId = Integer.parseInt(category.get("sid").toString());
		for(Map<String,Object> map:illness){
			map.put("categoryId", categoryId);
		}
		this.illnessService.insertIllnessByBatch(illness);//插入疾病库
		return 1;
	}

}
