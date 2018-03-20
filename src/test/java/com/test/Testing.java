package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.TokenInfo;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;


@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/spring-mvc.xml" })
public class Testing extends AbstractTestNGSpringContextTests{
    private static HttpSolrServer solrServer;

    static {
        solrServer = new HttpSolrServer("http://localhost:8888/solr/test");
        solrServer.setConnectionTimeout(5000);
    }
	
    @org.junit.Test
    public void test()  throws Exception {
    	try {
    		SolrQuery solrQuery = new SolrQuery();
        	solrQuery.set("q", "goods_name:*折扣*");
        	QueryResponse query = solrServer.query(solrQuery);
        	SolrDocumentList results = query.getResults();
        	for (SolrDocument solrDocument : results) {
    			System.out.println(solrDocument.get("goods_name"));
    			System.out.println(solrDocument.get("goods_id"));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
     
    public static void main(String[] args) throws SolrServerException, IOException {
        SolrServer solr=null;
        SolrInputDocument document=new SolrInputDocument();
        String url="http://localhost:8888/solr/test";
        
        //solr库添加索引
        solr=new HttpSolrServer(url);
        document.addField("goods_id", "123");
        document.addField("goods_name", "shusheng");
        solr.add(document);
        
        ShopncGoods shopncGoods = new ShopncGoods();
        shopncGoods.setGoods_id(3);
        shopncGoods.setGoods_name("333");
        shopncGoods.setStore_name("444");
//        solr.addBean(shopncGoods);
        solr.commit();

        System.out.println(getAnalysis("折扣商品"));
    }
    
    public static void test2() throws SolrServerException{
    	SolrServer solr=null;
        String url="http://localhost:8888/solr/test";
        solr=new HttpSolrServer(url);
    	 //查询solr库
//      SolrQuery query = new SolrQuery("goods_name:折扣");
        SolrQuery query = new SolrQuery("*");
        query.setStart(0);//起始页
        query.setRows(3);//每页显示数量
        QueryResponse rsp = solr.query( query );
        SolrDocumentList results = rsp.getResults();
        System.out.println(results.getNumFound());//查询总条数
        for(SolrDocument doc:results){
            System.out.println(doc);
            System.out.println(doc.get("goods_id")+"----------------------");
            System.out.println(doc.get("goods_name")+"----------------------");
            System.out.println(doc.get("store_name")+"----------------------");
        }
    }
    

    public static List<String> getAnalysis(String sentence) {
        FieldAnalysisRequest request = new FieldAnalysisRequest(
                "/analysis/field");
        request.addFieldName("title");// 字段名，随便指定一个支持中文分词的字段
        request.setFieldValue("");// 字段值，可以为空字符串，但是需要显式指定此参数
        request.setQuery(sentence);

        FieldAnalysisResponse response = null;
        try {
            response = request.process(solrServer);
        } catch (Exception e) {
            System.out.println("获取查询语句的分词时遇到错误");
        }

        List<String> results = new ArrayList<String>();
        Iterator<AnalysisPhase> it = response.getFieldNameAnalysis("title")
                .getQueryPhases().iterator();
        while(it.hasNext()) {
          AnalysisPhase pharse = (AnalysisPhase)it.next();
          List<TokenInfo> list = pharse.getTokens();
          for (TokenInfo info : list) {
              results.add(info.getText());
          }

        }

        return results;
    }


}
