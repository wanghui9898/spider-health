package com.j1.health.spider.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.j1.health.util.CollectionUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Service("illnessCauseUrlHandler")
public class IllnessCauseUrlHandler {
	
	/**
	 * 获取url
	 * @param page
	 */
	public void dealIllnessCauseUrl(Page page){
		List<String> strs = page.getHtml().xpath("//div[@class='jb-con mt10 pl20']/").all();
		if(null != strs && strs.size() > 1){
			String div = strs.get(1);
			Html html = new Html(div);
			String value = html.links().toString();
			//获取队列副本参数做比对
			Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
			String url = page.getRequest().getUrl();
			for(Map<String,Object> map:copyQueue){
				if(map.containsKey(url)){
					//把value 和 url 存到数据库
					Map<String,Object> resultMap = new LinkedHashMap<>();
					resultMap.put("illnessId", map.get(url));
					resultMap.put("illnessCauseUrl", value);
					resultMap.put("version", "1");//以后做活
					CollectionUtil.putStringToList(resultMap);
				}
			}
		}
	}

}
