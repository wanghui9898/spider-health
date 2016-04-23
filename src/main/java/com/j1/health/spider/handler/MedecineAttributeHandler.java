package com.j1.health.spider.handler;

import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Page;

@Service("medecineAttributeHandler")
public class MedecineAttributeHandler {
	
	public void handler(Page page){
		System.out.println(page);
		String medecine_name = "";//1.药品名称
		String approval_number = "";//2.批准文号
		String medecine_type = "";//3.是否处方药
		String medecine_element = "";//4.药的成分
		String medecine_insurance_type = "";//5.医保的类型
		String medecine_product = "";//6.药的生产企业
		String medecine_function = "";//7.药的主治功能
		String medecine_made = "";//8.药的来源 国产/进口
		String medecine_usage = "";//9.用法用量
		String medecine_illness = "";//10.相关疾病
		String medecine_common_name = "";//11.药品通用名称
		String medecine_drug = "";//12 剂型
		String medecine_effect = "";//13.不良反应
		String medecine_taboo = "";//14.禁忌
		String medecine_attention = "";//15.注意事项
		String medecine_action = "";//16药理作用
		String medecine_zylb = "";//17.作用类别
		String medecine_ywxhzy = "";//18.药物相互作用
		
		
	}

}
