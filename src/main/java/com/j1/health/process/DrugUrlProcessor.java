package com.j1.health.process;

import com.j1.health.handler.CommonHander;
import com.j1.health.handler.MedicineHandler;
import com.j1.health.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * 处理药品url
 * @author 辉
 *
 */
public class DrugUrlProcessor extends BasePageProcess{
	
	private String url;//处理的url
	
	private CommonHander medicineHandler;
	
	public DrugUrlProcessor(String url) {
		this.url = url;
	}

	public void process(Page page) {
		CommonHander medicineHandlers = new MedicineHandler();
		try {
			medicineHandlers.handlePage(page, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Site getSite() {
		return super.getSite();
	}

}
