package com.j1.health.process.category;

import org.springframework.stereotype.Service;

import com.j1.health.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

/**
 * 抓取第一级类目
 * @author 辉
 *
 */
@Service("categoryProcessor")
public class CategoryProcessor extends BasePageProcess{
	
	
	public void process(Page page) {
		System.out.println(page);
	}

}
