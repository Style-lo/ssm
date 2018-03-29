package com.ssm.api.controller.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.api.bean.entity.ShopncGoods;
import com.ssm.api.service.solrImpl.SolrServiceImpl;

@Controller
@RequestMapping("api/v1/solr")
public class SolrController {

	@Autowired
	SolrServiceImpl solrServiceImpl;
	@Autowired
	SolrServer solrServer;
	
	/**
	 * 全量更新solr库，推荐网页上操作
	 */
	@RequestMapping("insertSolr")
	public void insertSolr(){
		boolean updateGoods = solrServiceImpl.insertSolr();
		String ss = updateGoods ? "成功": "失败";
		System.out.println(ss);
	}
	
	
	/**
	 * 单个商品更新
	 * @param store_id
	 */
	@RequestMapping("updateOneGoods/{goods_id}")
	public void updateGoods(@PathVariable("goods_id") Integer goods_id){
		boolean updateGoods = solrServiceImpl.updateGoods(goods_id);
		String ss = updateGoods ? "成功": "失败";
		System.out.println(ss);
	}
	
	/**
	 * 单家店铺更新
	 * @param store_id
	 */
	@RequestMapping("updateOneStore/{store_id}")
	public void updateStore(@PathVariable("store_id") Integer store_id){
		boolean updateStore = solrServiceImpl.updateStore(store_id);
		String ss = updateStore ? "成功": "失败";
		System.out.println(ss);
	}
	
	
	/**
	 * Suggest 智能提示
	 * @param work
	 */
	@RequestMapping("getSuggest/{work}")
	public List<String> getSuggest(@PathVariable("work") String work){
		List<String> prompt = null;;
		 try {
			prompt = solrServiceImpl.getSuggest("cs",null);
			System.out.println(prompt);
		} catch (Exception e) {
			e.printStackTrace();
			prompt = new ArrayList<String>();
		}
		return prompt;
	}
	
	/**
	 * 搜索查询
	 * @param work
	 */
	@RequestMapping("getWord/{work}")
	public void updateStore(@PathVariable("work") String work){
		 try {
			List<ShopncGoods> list = solrServiceImpl.getSolrQuerys(work, new HashMap());
			for (ShopncGoods shopncGoods : list) {
				System.out.println(shopncGoods.getGoods_id());
				System.out.println(shopncGoods.getGoods_name());
				System.out.println(shopncGoods.getStore_name());
			}
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
