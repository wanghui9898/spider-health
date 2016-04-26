package com.j1.health.spider.process.illness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.IllnessAttributeValueHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

/**
 * 
 * @author 王辉
 *
 */
@Service("illnessAttributeValueProcess")
public class IllnessAttributeValueProcess extends BasePageProcess{
	
	private String type;//根据类型处理不同的页面
	
	@Autowired
	private IllnessAttributeValueHandler illnessAttributeValueHandler;
	
	public void process(Page page) {
		this.illnessAttributeValueHandler.process(page, type);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
