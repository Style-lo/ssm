package com.ssm.api.service.solrImpl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.entity.ShopncGoods;
import com.ssm.api.bean.entity.ShopncStore;
import com.ssm.api.dao.SolrDao;
import com.ssm.api.service.solr.SolrService;


@Service
public class SolrServiceImpl implements SolrService{

	@Autowired 
	private SolrDao solrDao;
	@Autowired
    private SolrServer solrServer;
	
	
	/** (non-Javadoc)
	 * solr库单个商品更新
	 */
	@Override
	public boolean updateGoods(Integer goods_id) {
		try {
		ShopncGoods bygoodsId = solrDao.getBygoodsId(goods_id);
		
		//删除为负数的goods_id(因为新增店铺到solr的时候店铺可能没商品,这时候把店铺id做为goods_id存在solr库)
			solrServer.deleteById("-"+bygoodsId.getStore_id());
			solrServer.addBean(bygoodsId);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * solr库单个店铺更新
	 */
	@Override
	public boolean updateStore(Integer store_id) {
		List<ShopncGoods> storeIdgetGoods = null;
		int start=0;
		try {
			if (store_id > 0) {
				while (true){
				storeIdgetGoods = solrDao.storeIdgetGoods(store_id,start,500);
				//店铺没商品的时候把店铺id设置为solr的主键goods_id
				 if (storeIdgetGoods == null || storeIdgetGoods.size() < 1) {
					 if (start==0) {
						 ShopncStore shopncStore = solrDao.getBystoreId(store_id);
						 ShopncGoods goods = new ShopncGoods();
						 goods.setGoods_id(-shopncStore.getStore_id());
						 goods.setStore_name(shopncStore.getStore_name());
						 solrServer.addBean(goods);
						 solrServer.commit();
						 return true;
					}
				}
				 
				 if(start==0){//把之前的负数的goods_id删除掉
	                 solrServer.deleteById("-"+store_id);
	             }
	             start+=500;
	             //不为空，则加入solr中
	             solrServer.addBeans(storeIdgetGoods);
	             solrServer.commit();
	             if(storeIdgetGoods!=null&&!storeIdgetGoods.isEmpty()){
	            	 storeIdgetGoods.clear();//清空集合，释放内存
	             }
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	public void ss() throws SolrServerException{
//		solrServer = getSolrServer();//查询关键词是否存在
        StringBuilder sb = new StringBuilder();
        sb.append("kw:").append("小龟");
        SolrQuery query = new SolrQuery();
        query.setQuery(sb.toString());
        //执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
	}
}
