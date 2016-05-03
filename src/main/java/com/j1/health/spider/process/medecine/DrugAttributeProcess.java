package com.j1.health.spider.process.medecine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.DrugAttributeHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

@Service("drugAttributeProcess")
public class DrugAttributeProcess extends BasePageProcess{
	
	@Autowired
	private DrugAttributeHandler drugAttributeHandler;
	
	public void process(Page page) {
		this.drugAttributeHandler.process(page);
	}

}
