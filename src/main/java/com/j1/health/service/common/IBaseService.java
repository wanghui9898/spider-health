package com.j1.health.service.common;

import java.util.List;
import java.util.Map;

public interface IBaseService<T,PK> {
	
	T get(PK id);

    int saveOrUpdate(T entity);

    int delete(PK id);
    
    List<T>  getAll(Map<String, Object> reqParam);
    
}
