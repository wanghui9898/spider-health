package com.j1.health.spider.handler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.j1.health.util.CollectionUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Service("medicineHandler")
public class MedicineHandler{
	
	
	public void processPage(Page page){
		List<String> selectAble = page.getHtml().xpath("//div[@class='jb-cpc-list mt10 clearfix']/").all();
		String access_url= page.getRequest().getUrl();
		for(String str:selectAble){
			Html html = new Html(str);
			String medecine_name = html.xpath("//div[@class='s-list-btn']/div/div/span/a/text()").toString();//1.获取药品名字
			String medecine_url = html.xpath("//div[@class='s-list-btn']/div/div/span/a/@href").toString();//2.获取药品url
			String medecine_price =  html.xpath("//div[@class='s-list-btn']/div/div/span[3]/text()").toString();//3.药品价格
			String medecine_product_name = html.xpath("//div[@class='s-list-btn']/div/div/span[2]/text()").toString();//4.药品生产公司
			String medecine_image_url = html.xpath("//div[@class='s-mlist-con clearfix']/div/a/img/@src").toString();//5.药品图片url
			Set<Map<String,Object>> url_queue = CollectionUtil.getDuplicateQueue();
			for(Map<String,Object> url_map:url_queue){
				if(url_map.containsKey(access_url)){
					Map<String,Object> map = Maps.newLinkedHashMap();
					Object illness_id = url_map.get(access_url);
					map.put("medecineName", medecine_name);
					map.put("medecineUrl", medecine_url);
					map.put("medecinePrice", medecine_price);
					map.put("medecineProductName", medecine_product_name);
					map.put("medecineImageUrl", medecine_image_url);
					map.put("illnessId", illness_id);
					CollectionUtil.putStringToList(map);
				}
			}
		}
	}

}
