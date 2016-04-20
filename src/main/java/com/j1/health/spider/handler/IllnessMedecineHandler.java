package com.j1.health.spider.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.j1.health.util.CollectionUtil;

import us.codecraft.webmagic.Page;

@Service("illnessMedecineHandler")
public class IllnessMedecineHandler {
	
	/**
	 * 疾病对应的商品url
	 * @param page
	 */
	public void dealIllnessMedecineUrl(Page page){
		List<String> str = page.getHtml().xpath("//script/outerHtml()").all();
		String access_url = page.getRequest().getUrl();
		for(String value:str){
			value = value.replaceAll("\n", "").replaceAll(" ", "").replaceAll("\t", "");
			if(value.matches("<script>.*.pageFlie.*</script>")){
				String cV = value.substring(value.indexOf("page:"), value.indexOf("urlSuffix")-1);
				if(null != cV && !"".equals(cV)){
					String[] arrayStr = StringUtils.split(cV, ",");
					if(null != arrayStr && arrayStr.length > 0){
						for(String cStr:arrayStr){
							if(cStr.matches("count:.*")){
								String count = cStr.substring(cStr.indexOf(":")+1);//获取总共有多少页
								Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
								for(Map<String,Object> map:copyQueue){
									if(map.containsKey(access_url)){
										//把value 和 url 存到数据库
										Map<String,Object> resultMap = new LinkedHashMap<>();
										resultMap.put("illnessId", map.get(access_url));
										resultMap.put("illnessMedecineUrl", access_url);
										resultMap.put("count", count);
										CollectionUtil.putStringToList(resultMap);
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

}
