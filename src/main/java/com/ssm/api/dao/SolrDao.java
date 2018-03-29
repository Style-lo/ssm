package com.ssm.api.dao;

import java.util.List;

import com.ssm.api.bean.entity.ShopncGoods;
import com.ssm.api.bean.entity.ShopncStore;

public interface SolrDao {

	ShopncGoods getBygoodsId(Integer goods_id);
	
	List<ShopncGoods> storeIdgetGoods(Integer store_id,int statr,int end);
	
	ShopncStore getBystoreId(Integer store_id);

	List<ShopncGoods> getAllGoods();

	List<ShopncGoods> getAllGoods(int start, int limit);
}
