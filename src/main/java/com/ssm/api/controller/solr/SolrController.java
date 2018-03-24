package com.ssm.api.controller.solr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.api.service.solrImpl.SolrServiceImpl;

@Controller
@RequestMapping("api/v1/solr")
public class SolrController {

	@Autowired
	SolrServiceImpl solrServiceImpl;
	
	@RequestMapping("updateOneGoods")
	public void updateGoods(){
		boolean updateGoods = solrServiceImpl.updateGoods(2);
		String ss = updateGoods ? "成功": "失败";
		System.out.println(ss);
	}
	@RequestMapping("updateOneStore")
	public void updateStore(@RequestParam Integer store_id){
		boolean updateGoods = solrServiceImpl.updateStore(store_id);
		String ss = updateGoods ? "成功": "失败";
		System.out.println(ss);
	}
}
