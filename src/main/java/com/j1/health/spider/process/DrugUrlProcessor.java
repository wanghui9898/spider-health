package com.j1.health.spider.process;

import java.util.List;

import com.j1.health.spider.process.base.BasePageProcess;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

/**
 * 处理药品url
 * @author 辉
 *
 */
public class DrugUrlProcessor extends BasePageProcess{

	public void process(Page page) {
		List<String> strs = page.getHtml().xpath("//div[@class='jb-con mt10 pl20']/").all();
		if(null != strs && strs.size() > 1){
			String div = strs.get(1);
			Html html = new Html(div);
			String value = html.links().toString();
			System.out.println(value);
		}
	}

}
