package com.j1.health.spider.process.category;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.j1.health.spider.process.base.BasePageProcess;
import com.j1.health.util.ConsField;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 抓取第一级类目
 * @author 辉
 *
 */
@Service("categoryProcessor")
public class CategoryProcessor extends BasePageProcess{
	
	private static Log logger = LogFactory.getLog(CategoryProcessor.class);
	
	public void process(Page page) {
		//1.找到一级类目所有的节点
		Selectable categorys = page.getHtml().xpath("div[@class='col-main mt10 fr']/outerHtml()");
		//2.获取一级类目
		Html categoryHtml = new Html(categorys.toString());
		Map<String,Object> map = new LinkedHashMap<>();
		List<String> category = categoryHtml.xpath("h3/text()").all();//获取一级类目的名字
		int j = 0;
		if(null != category && category.size() > 0){
			for(int i=0;i<category.size();i++){
				int k = i+j+2;
				j++;
				Selectable select = categoryHtml.xpath("//div[@class='col-main mt10 fr']/div/div["+k+"]/html()");
				Html html = new Html(select.toString());
				List<String> urls = html.xpath("//ul[@class='f14 blue-a normal-a clearfix rel-dis-drug']/li/a/@href").all();
				List<String> title = html.xpath("//ul[@class='f14 blue-a normal-a clearfix rel-dis-drug']/li/a/@title").all();
				map.put(ConsField.URL+j, urls);
				map.put(ConsField.Title+j, title);
				map.put(ConsField.Category+j, category.get(i));
			}
			page.putField(ConsField.Illness, map);
		}
	}

}
