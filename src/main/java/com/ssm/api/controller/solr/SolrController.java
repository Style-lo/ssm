package com.ssm.api.controller.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
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
	@Autowired
	SolrServer solrServer;
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
	
	
/*	@RequestMapping("getWord")
	public void updateStore(@RequestParam String work){
		List<String> suggestedWords = solrServiceImpl.getPrompt(solrServer,work,5);
		for (String string : suggestedWords) {
			System.out.println(string);
		}
	}*/
	@RequestMapping("getWord")
	public void updateStore(@RequestParam String work){
		 try {
			solrServiceImpl.getPrompt("zk",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
