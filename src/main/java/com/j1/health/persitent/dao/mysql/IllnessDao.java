package com.j1.health.persitent.dao.mysql;

import java.util.List;
import java.util.Map;

import com.j1.health.persitent.dao.base.CrudDao;
import com.j1.health.persitent.dao.base.MyBatisRepository;

@MyBatisRepository("illness")
public interface IllnessDao extends CrudDao{
	
	public int insertIllnessByBatch(List<Map<String,Object>> list);
	
	public List<Map<String,Object>> getIllnessList();//获取疾病id和url
	
	public List<Map<String, Object>> getErrorIllnessList();//抓取没有爬虫下来的url
	
	public List<Map<String,Object>> getIllnessMedecineList();//获取疾病对应的商品url
	
	public int insertIllnessCauseByBatch(List<Map<String,Object>> list);//批量插入数据
	
	public int insertIllnessMedecineByBatch(List<Map<String,Object>> param);//插入疾病和医药url

}
