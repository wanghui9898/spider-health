package com.j1.health.persitent.dao.mysql;

import java.util.List;
import java.util.Map;

import com.j1.health.persitent.dao.base.MyBatisRepository;

/**
 * 获取医药类的服务
 * @author 王辉
 *
 */
@MyBatisRepository("drug")
public interface DrugDao {
	
	public List<Map<String, Object>> getDrugList(Map<String, Object> param);
	
	public int insertDrugByBatch(List<Map<String, Object>> lists);

}
