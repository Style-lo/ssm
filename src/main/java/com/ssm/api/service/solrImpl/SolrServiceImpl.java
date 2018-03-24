package com.ssm.api.service.solrImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.entity.ShopncGoods;
import com.ssm.api.bean.entity.ShopncStore;
import com.ssm.api.bean.entity.SolrResults;
import com.ssm.api.dao.SolrDao;
import com.ssm.api.service.solr.SolrService;


@Service
public class SolrServiceImpl implements SolrService{

	@Autowired 
	private SolrDao solrDao;
	@Autowired
    private SolrServer solrServer;
	
	public static final String testURl="http://127.0.0.1:8888/solr/test";
	
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

	public void ss() throws SolrServerException{
//		solrServer = getSolrServer();//查询关键词是否存在
        StringBuilder sb = new StringBuilder();
        sb.append("kw:").append("小龟");
        SolrQuery query = new SolrQuery();
        query.setQuery(sb.toString());
        //执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        System.out.println(response);
	}
	
	public List<String> getPrompt( String prefix, Integer limit) {
        if(limit==null)limit =5;//默认五个
        if (prefix==null||"".equals(prefix.trim())||limit<=0){//符合这些条件直接返回空
            return ListUtils.EMPTY_LIST;
        }
//        SolrServer solrServer = null;
        try {
//            solrServer = getSolrServer();
        	SolrServer server = new HttpSolrServer("http://127.0.0.1:8888/solr/test");
            SolrQuery query = getSuggestQuery(prefix, limit);
            //执行查询。得到一个Response对象。
            QueryResponse response = server.query(query);
            if(response.getResults().getNumFound()>0L){//有记录
                //更新不为空的字段
                SolrDocumentList results = response.getResults();
                SolrResults<String> solrResults = this.toBeanSolrResults(response.getResults(), String.class);
                if(solrResults!=null){
                    return solrResults.getDocs();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ListUtils.EMPTY_LIST;
        }
        return ListUtils.EMPTY_LIST;
    }
	
	private SolrQuery getSuggestQuery(String prefix, Integer limit) {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder sb = new StringBuilder();
        //字母则会小写，汉字不变 并且kw不等于自己
        sb.append("suggest:").append(prefix.toLowerCase()).append("* AND -kw:").append(prefix.toLowerCase());
        solrQuery.setQuery(sb.toString());
        solrQuery.addFilterQuery("kwfreq:[100 TO *]");//频率大于等于多少才输出
        solrQuery.addField("kw");
        //solrQuery.addField("kwfreq");
        solrQuery.addSort("kwfreq", SolrQuery.ORDER.desc);
        solrQuery.setStart(0);
        solrQuery.setRows(limit);
        return solrQuery;
    }
	 public  SolrResults toBeanSolrResults(SolrDocumentList records, Class clazz){
	        SolrResults solrResults = new SolrResults();
	        if(records!=null){
	            List list = new ArrayList();
	            if(clazz.equals(Integer.class)){
	                for(SolrDocument record : records){
	                    list.add((Integer) record.values().toArray()[0]);
	                }
	            }else if(clazz.equals(String.class)){
	                for(SolrDocument record : records) {
	                    list.add((String) record.values().toArray()[0]);
	                }
	            }else {
	                for(SolrDocument record : records){
	                    list.add(toBean(record,clazz));
	                }
	            }
	            solrResults.setNumFound(records.getNumFound());
	            solrResults.setStart(records.getStart());
	            solrResults.setDocs(list);
	        }
	        return solrResults;
	    }
	 
	   /**
	     * 将SolrDocument转换成Bean
	     * @param record
	     * @param clazz
	     * @return
	     */
	    public static Object toBean(SolrDocument record, Class clazz){
	        Object obj = null;
	        try {
	            obj = clazz.newInstance();
	        } catch (InstantiationException e1) {
	            e1.printStackTrace();
	        } catch (IllegalAccessException e1) {
	            e1.printStackTrace();
	        }
	        Field[] fields = clazz.getDeclaredFields();
	        for(Field field:fields){
	            boolean fieldHasAnno = field.isAnnotationPresent(org.apache.solr.client.solrj.beans.Field.class);
	            Object value = null;//赋值value
	            if(fieldHasAnno){//有注解Field不是默认的
	                org.apache.solr.client.solrj.beans.Field fieldAnno = field.getAnnotation(org.apache.solr.client.solrj.beans.Field.class);
	                if(!"#default".equals(fieldAnno.value())){
	                    value = record.get(fieldAnno.value());
	                }
	            }
	            if(value == null){
	                value = record.get(field.getName());
	            }
	            try {
	                BeanUtils.setProperty(obj, field.getName(), value);
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	        }
	        return obj;
	    }
}
