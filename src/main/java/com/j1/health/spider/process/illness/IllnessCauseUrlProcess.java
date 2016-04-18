package com.j1.health.spider.process.illness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.IllnessCauseUrlHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

@Service("illnessCauseUrlProcess")
public class IllnessCauseUrlProcess extends BasePageProcess{
	
	@Autowired
	private IllnessCauseUrlHandler illnessCauseUrlHandler;//疾病

	@Override
	public void process(Page page) {
		this.illnessCauseUrlHandler.dealIllnessCauseUrl(page);
	}

}
