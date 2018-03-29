package com.ssm.api.service.solr;

public interface SolrService {

	//根据商品id添加到solr库
	boolean updateGoods(Integer goods_id);
	//根据店铺id添加到solr库
	boolean updateStore(Integer store_id);
	//添加推荐词或已存在关键词值+1
    boolean addPromptWords(String word);
}
