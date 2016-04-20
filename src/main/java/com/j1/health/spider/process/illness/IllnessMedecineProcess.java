package com.j1.health.spider.process.illness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.IllnessMedecineHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

@Service("illnessMedecineProcess")
public class IllnessMedecineProcess extends BasePageProcess{
	
	@Autowired
	private IllnessMedecineHandler illnessMedecineHandler;//疾病

	@Override
	public void process(Page page) {
		this.illnessMedecineHandler.dealIllnessMedecineUrl(page);
	}

}
