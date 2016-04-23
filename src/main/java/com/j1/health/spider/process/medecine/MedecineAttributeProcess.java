package com.j1.health.spider.process.medecine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.MedecineAttributeHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

@Service("medecineAttributeProcess")
public class MedecineAttributeProcess extends BasePageProcess{
	
	@Autowired
	private MedecineAttributeHandler medecineAttributeHandler;
	
	public void process(Page page) {
		this.medecineAttributeHandler.handler(page);
	}

}
