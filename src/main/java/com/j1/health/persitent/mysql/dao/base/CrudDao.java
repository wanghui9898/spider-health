package com.j1.health.persitent.mysql.dao.base;

import java.util.List;
import java.util.Map;

public interface CrudDao<PK, T> {
	
	T findOne(PK id);

    List<T> findAll(Map<String, Object> reqParam);

    int insert(T entity);

    int update(T entity);

    int delete(PK id);
}
