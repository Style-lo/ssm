package com.ssm.api.service.solr;

import java.util.List;
import java.util.Map;

import com.ssm.api.bean.entity.ShopncGoods;

public interface SolrService {

	//根据商品id添加到solr库
	boolean updateGoods(Integer goods_id);
	//根据店铺id添加到solr库
	boolean updateStore(Integer store_id);
	//添加推荐词或已存在关键词值+1
    boolean addPromptWords(String word);
    //搜索框查询
    List<ShopncGoods> getSolrQuerys(String word, Map map);
}
