package com.j1.health.handler;

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
