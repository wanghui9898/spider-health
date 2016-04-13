package com.j1.health.spider.handler;

import com.j1.health.spider.handler.base.CommonHander;

import us.codecraft.webmagic.Page;

public class MedicineHandler implements CommonHander{

	@Override
	public void handlePage(Page page, String url) throws Exception {
		System.out.println("method invoke...");
		if(null == page){
			System.out.println(url);
		}
	}

}
