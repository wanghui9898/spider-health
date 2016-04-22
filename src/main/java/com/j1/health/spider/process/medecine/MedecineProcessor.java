package com.j1.health.spider.process.medecine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j1.health.spider.handler.MedicineHandler;
import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;

@Service("medecineProcessor")
public class MedecineProcessor extends BasePageProcess{
	
	@Autowired
	private MedicineHandler medicineHandler;
	
	/**
	 * 获取药品url,img_url,name,price
	 */
	public void process(Page page) {
		this.medicineHandler.processPage(page);
	}

}
