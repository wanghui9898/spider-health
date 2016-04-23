package com.j1.health.spider.process.illness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.IllnessAttributeHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

/**
 * 疾病属性
 * @author 王辉
 *
 */
@Service("illnessAttributeProcess")
public class IllnessAttributeProcess extends BasePageProcess{
	
	@Autowired
	private IllnessAttributeHandler illnessAttributeHandler;
	
	/**
	 * 
	 */
	public void process(Page page) {
		this.illnessAttributeHandler.process(page);
	}

}
