package com.ssm.api.service.solrImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.entity.KeyWord;
import com.ssm.api.bean.entity.ShopncGoods;
import com.ssm.api.bean.entity.ShopncStore;
import com.ssm.api.bean.entity.SolrResults;
import com.ssm.api.dao.SolrDao;
import com.ssm.api.service.solr.SolrService;
import com.ssm.api.utils.PinYinUtils;
import com.ssm.api.utils.SolrUtils;


@Service
public class SolrServiceImpl implements SolrService{

	@Autowired 
	private SolrDao solrDao;
	@Autowired
    private SolrServer solrServer;
	
	public static final String solrURL="http://127.0.0.1:8888/solr/test";
	public static SolrServer getSolrService(){
		SolrServer solrServer = new HttpSolrServer(solrURL);
		return solrServer;
	}
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
				while (true){//防止数据量太大，一次500条数据
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

	
	/**
	 * suggest智能提示
	 * @param work 查询的字段
	 * @param limit	分页
	 * @return
	 */
	public List<String> getSuggest(String work, Integer limit){
		if(limit == null) limit=2;
		if(work == null || "".equals(work)) return null;
		
		try {
			SolrServer server = getSolrService();
			SolrQuery query = getSolrQuery(work,limit);
			//执行查询
			QueryResponse response = server.query(query);
			//获取文档列表
	        SolrDocumentList documentList = response.getResults();
	        if (documentList.getNumFound() > 0) {
	        	SolrResults<String> solrDocumentListToBean = this.solrDocumentListToBean(documentList, String.class);//转换成实体bean
	        	return solrDocumentListToBean.getDocs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ListUtils.EMPTY_LIST;//返回一个空列表 [ ]
	}
	
	/**
	 * 添加查询条件
	 * @param work
	 * @param limit
	 * @return
	 */
	public SolrQuery getSolrQuery(String work, Integer limit){
		SolrQuery query = new SolrQuery();
		StringBuilder sb = new StringBuilder();
		//查询条件		字母则会小写，汉字不变 并且kw不等于自己  (-kw)
		//例如 work="水果" 则不提示水果，会提示水果篮等,如没有(-kw)或者去掉(-)会提示水果
		sb.append("suggest:").append(work.toLowerCase()).append("* AND -kw:").append(work.toLowerCase());
		//添加需要回显得内容
		query.setQuery(sb.toString());
        query.addField("kw");
        query.setSort("kwfreq", ORDER.desc);//按照id降序排列
//        query.addFilterQuery("kwfreq:[20 TO *]");//频率大于等于(20)多少才输出
        query.setRows(limit);//设置每页显示多少条
		return query;
	}
	/**
	 *  SolrDocumentList转换实体bean
	 * @param documentList
	 * @return
	 */
	public SolrResults<String> solrDocumentListToBean(SolrDocumentList documentList, Class clazz){
		SolrResults<String> results = null;
		List<String> list= null;
		if (documentList != null) {
			results = new SolrResults<String>();
			list = new ArrayList<String>();
			if (clazz.equals(String.class)) {
				for (SolrDocument solrDocument : documentList) {
					//SolrDocument{kw=折扣} 所以我toArray[0] 拿它的第一个值
					list.add(solrDocument.values().toArray()[0].toString());
				}
			}else {
				/*for (SolrDocument solrDocument : documentList) {
					//SolrDocument{kw=折扣} 所以我toArray[0] 拿它的第一个值
					list.add(solrDocument.values());
				}*/
			}
			results.setDocs(list);
			results.setNumFound(documentList.getNumFound());//总条数
			results.setStart(documentList.getStart());//开始数
		}
		return results;
	}
	
	
	/**
	 * 添加推荐词或推荐词搜索频率+1
	 */
	@Override
	public boolean addPromptWords(String word) {
		if(word == null || "".equals(word)) return false;
		KeyWord keyWord = new KeyWord();
		try {
			SolrServer solrServer = getSolrService();
			SolrQuery query = new SolrQuery();
			StringBuilder sb = new StringBuilder();
            sb.append("kw:").append(word);
            query.setQuery(sb.toString());
            
			QueryResponse response = solrServer.query(query);
			SolrDocumentList results = response.getResults();
			if (results.getNumFound() > 0) {//solr已有索引更新搜索频率
				SolrDocument solrDocument2 = results.get(0);
				solrDocument2.getFieldValue("kw");
				solrDocument2.getFieldValue("kwfreq");
                //更新不为空的字段
                SolrInputDocument doc = new SolrInputDocument();
                //先设置id
                doc.addField("kw", solrDocument2.getFieldValue("kw"));
                Map map = new HashMap();
                //搜索频率 +1
                map.put("set", solrDocument2.getFieldValue("kwfreq")==null ? 1:((Integer)solrDocument2.getFieldValue("kwfreq"))+1);
               //直接设值doc.addField("kwfreq", 20);会把索引中其他东西删除
               //需要doc.addField("kwfreq", {set=20});需要加一个set才不会删除其他东西
                doc.addField("kwfreq", map);
                solrServer.add(doc);//添加近solr中
			}else {//solr中没有索引，添加新索引
				keyWord.setKw(word);
				keyWord.setPinyin(Arrays.asList(PinYinUtils.cnToSpell(word)));
				keyWord.setAbbre(Arrays.asList(PinYinUtils.cnToFirstSpell(word)));
				keyWord.setKwfreq(1);
				solrServer.addBean(keyWord);
			}
			solrServer.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
