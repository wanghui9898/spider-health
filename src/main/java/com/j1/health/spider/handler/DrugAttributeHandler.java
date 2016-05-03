package com.j1.health.spider.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.j1.health.util.CollectionUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

@Service("drugAttributeHandler")
public class DrugAttributeHandler {
	
	public void process(Page page){
		Selectable selectable1 = page.getHtml().xpath("div[@class='p-inf fr mr20 mt20']");//获取药品名称
		Selectable selectable2 = page.getHtml().xpath("div[@class='clearfix pl20 pr20']");//获取药品说明书
		Set<Map<String,Object>> copyQueue = CollectionUtil.getDuplicateQueue();
		String url = page.getRequest().getUrl();
		Map<String,Object> map1 = getFiledFromSelectable1(selectable1);
		Map<String,Object> map2 = getFiledFromSelectable2(selectable2);
		Map<String,Object> total_map = Maps.newLinkedHashMap();
		total_map.putAll(map1);
		total_map.putAll(map2);
		for(Map<String,Object> map:copyQueue){
			if(map.containsKey(url)){
				total_map.put("illness_id", map.get(url));
				CollectionUtil.putStringToList(total_map);
			}
		}
	}
	
	private Map<String,Object> getFiledFromSelectable1(Selectable select1){
		Map<String,Object> map = Maps.newLinkedHashMap();
		Html html = new Html(select1.toString());
		String drug_name = html.xpath("div[@class='p-inf fr mr20 mt20']/h1/text()").toString();//药品名字
		String drug_recipe = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[1]/div[1]/text()").toString();//处方药/甲类/乙类
		String drug_insurance = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[1]/div[3]/text()").toString();//医保类
		String drug_china_4 = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[1]/div[4]/text()").toString();//国产或者进口
		String drug_china_5 = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[1]/div[5]/text()").toString();//国产或者进口
		String drug_approve_number_prefix = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[2]/div[1]/text()").toString();//批准文号前缀
		List<String> drug_approve_number_postfix = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[2]/div[2]/span/span/b/@style").all();//批准文号坐标
		List<String> drug_approve_number = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[2]/div[2]/span/span/b/text()").all();//批准文号坐标
		String str = generate_Str(drug_approve_number_postfix,drug_approve_number);
		if(null != drug_approve_number_prefix && drug_approve_number_prefix.length() > 0){
			drug_approve_number_prefix = drug_approve_number_prefix.trim();
			drug_approve_number_prefix = drug_approve_number_prefix + str;
		}else{
			drug_approve_number_prefix = str;
		}
		String drug_product_enterprice = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[3]/a/text()").toString();//生产企业
		List<String> drug_illness = html.xpath("div[@class='p-inf fr mr20 mt20']/ul/li[6]/div[2]/html()").all();
		StringBuffer sbf = new StringBuffer();
		if(null != drug_illness && drug_illness.size() > 0){
			//获取标签
			if(drug_illness.size() == 1){
				String illness_html = drug_illness.get(0);
				List<String> drug_ill = new Html(illness_html).xpath("//a/text()").all();
				int k = 0;
				for(int i=0;i<drug_ill.size();i++){
					k++;
					if(k == drug_ill.size()){//表示最后一个
						sbf.append(drug_ill.get(i));
					}else{
						sbf.append(drug_ill.get(i)).append(",");
					}
				}
			}else{
				int k = 0;
				for(int i=0;i<drug_illness.size();i++){
					k++;
					String illness_name = new Html(drug_illness.get(i)).xpath("//a/text()").toString();
					if(k == drug_illness.size()){//表示最后一个
						if(null != illness_name){
							sbf.append(illness_name);
						}
					}else{
						if(null != illness_name){
							sbf.append(illness_name).append(",");
						}
					}
				}
			}
		}
		String drug_entrance_china = "";
		if(null != drug_china_4){
			drug_china_4 = drug_china_4.trim();
		}
		if(null != drug_china_5){
			drug_china_5 = drug_china_5.trim();
		}
		if("进口".equals(drug_china_4)){
			drug_entrance_china = drug_china_4;
		}else if("进口".equals(drug_china_5)){
			drug_entrance_china = drug_china_5;
		}
		map.put("drug_name", drug_name);
		map.put("drug_recipe", drug_recipe);
		map.put("drug_insurance", drug_insurance);
		map.put("drug_entrance_china", drug_entrance_china);
		map.put("drug_approve_number", drug_approve_number_prefix);
		map.put("drug_product_enterprice", drug_product_enterprice);
		map.put("drug_illness", sbf.toString());
		return map;
	}
	
	/**
	 * 获取剂量
	 * @param select2
	 * @return
	 */
	private Map<String,Object> getFiledFromSelectable2(Selectable select2){
		Map<String,Object> map = Maps.newLinkedHashMap();
		Html html = new Html(select2.toString());
		String drug_common_name = html.xpath("div[@class='clearfix pl20 pr20']/ul[1]/li[2]/text()").toString();//药品通用名字
		String drug_major_function =  html.xpath("div[@class='clearfix pl20 pr20']/ul[2]/li[2]/text()").toString();//主治功能
		String drug_usage_dosage =  html.xpath("div[@class='clearfix pl20 pr20']/ul[3]/li[2]/text()").toString();//用法用量
		String drug_form =  html.xpath("div[@class='clearfix pl20 pr20']/ul[4]/li[2]/text()").toString();//剂型
		String drug_bad_react =  html.xpath("div[@class='clearfix pl20 pr20']/ul[5]/li[2]/text()").toString();//不良反应
		String drug_taboo = html.xpath("div[@class='clearfix pl20 pr20']/ul[6]/li[2]/text()").toString();//禁忌
		String drug_attention =  html.xpath("div[@class='clearfix pl20 pr20']/ul[7]/li[2]/text()").toString();//注意事项
		String drug_element =  html.xpath("div[@class='clearfix pl20 pr20']/ul[8]/li[2]/text()").toString();//成分/元素
		String drug_type = html.xpath("div[@class='clearfix pl20 pr20']/ul[9]/li[2]/text()").toString();//作用类别
		String drug_mutual_action = html.xpath("div[@class='clearfix pl20 pr20']/ul[10]/li[2]/text()").toString();//药物相互作用
		String drug_action = html.xpath("div[@class='clearfix pl20 pr20']/ul[11]/li[2]/text()").toString();//药理作用
		map.put("drug_common_name", drug_common_name);
		map.put("drug_major_function", drug_major_function);
		map.put("drug_usage_dosage", drug_usage_dosage);
		map.put("drug_form", drug_form);
		map.put("drug_bad_react", drug_bad_react);
		map.put("drug_taboo", drug_taboo);
		map.put("drug_attention", drug_attention);
		map.put("drug_element", drug_element);
		map.put("drug_type", drug_type);
		map.put("drug_mutual_action", drug_mutual_action);
		map.put("drug_mutual_action", drug_mutual_action);
		map.put("drug_action", drug_action);
		return map;
	}
	
	//拼接
	private String generate_Str(List<String> prefix,List<String> postfix){
		if(null != prefix && null != postfix && prefix.size() > 0 && postfix.size() > 0 && (prefix.size() == postfix.size())){
			String prestr = "";
			prestr = prefix.toString().replace("[", "").replace("]", "").replaceAll("left:", "").replaceAll("px;", "");
			String postStr = postfix.toString().replace("[", "").replace("]", "");
			String[] a = prestr.split(",");
			String[] b = postStr.split(",");
			Double[] d = new Double[a.length];
			Map<Double,String> m = Maps.newTreeMap();
			for(int i=0;i<a.length;i++){
				d[i] = Double.parseDouble(a[i]);
				m.put(d[i], b[i]);
			}
			List<Map.Entry<Double, String>> list = new ArrayList<Map.Entry<Double, String>>(m.entrySet());
			// 然后通过比较器来实现排序
			Collections.sort(list, new Comparator<Map.Entry<Double, String>>() {
				// 升序排序
				public int compare(Entry<Double, String> o1, Entry<Double, String> o2) {
					return o1.getKey().compareTo(o2.getKey());
				}
			});
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<Double, String> entry:m.entrySet()) { 
				sb.append(entry.getValue().trim());
			} 
			return sb.toString();
		}
		return "";
	}
}
