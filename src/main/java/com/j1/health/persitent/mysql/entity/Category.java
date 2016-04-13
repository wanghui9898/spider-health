package com.j1.health.persitent.mysql.entity;

import org.apache.ibatis.type.Alias;

import com.j1.health.persitent.mysql.entity.base.CommonEntity;


@Alias(value = "category")
public class Category extends CommonEntity{
	
	private Long sit;//主键
	
	private String item;//产品类别
	
	private String name;//类目名字
	
	private String version;//版本号

	public Long getSit() {
		return sit;
	}

	public void setSit(Long sit) {
		this.sit = sit;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
