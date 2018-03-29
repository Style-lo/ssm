package com.ssm.api.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase;
import org.apache.solr.client.solrj.response.AnalysisResponseBase.AnalysisPhase;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.ssm.api.bean.entity.SolrResults;

/**
 * Created by 非洲小黑 on 2017/11/14.
 */
public class SolrUtils {
    private final static String SUGGEST= "/suggest";
    public static SolrServer server;
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
    /**
     * 将SolrDocumentList转换成SolrResults
     * @param records
     * @param clazz
     * @return
     */
    public static SolrResults toBeanSolrResults(SolrDocumentList records, Class clazz){
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
     * 将groups转换成SolrResults
     * @param groups
     * @param clazz
     * @return
     */
    public static SolrResults toBeanSolrResults(List<Group> groups, Class clazz){
        SolrResults solrResults = new SolrResults();
        if(groups!=null){
            List list = new ArrayList();
            for(Group record : groups){
                if(record!=null)list.add(toBean(record.getResult().get(0),clazz));
            }
            solrResults.setDocs(list);
        }
        return solrResults;
    }
    //返回分词字符串
    public static String getIk(SolrServer solrServer,String sentence){
        boolean isTrue = false;
        //是否含有数字
        isTrue = hasDigit(sentence);
        //判断是否包含字母
        if(!isTrue){
            isTrue = hasStr(sentence);
        }
        String ik = sentence;
        if(sentence.length()==3&&!isTrue){//字长度是3或4而外处理下
            sentence+=sentence.substring(0,1)+sentence.substring(2,3);
        }else if(sentence.length()==4&&!isTrue){//
            sentence+=sentence.substring(0,1)+sentence.substring(2,4)+sentence.substring(1,2)+sentence.substring(3,4);
        }
        FieldAnalysisRequest request = new FieldAnalysisRequest(
                "/analysis/field");
        request.addFieldName("store_text");// 字段名，随便指定一个支持中文分词的字段
        request.setFieldValue("");// 字段值，可以为空字符串，但是需要显式指定此参数
        request.setQuery(sentence);

        FieldAnalysisResponse response = null;
        try {
            response = request.process(solrServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String word = "*"+ik+"* "+ik+" ";
        StringBuilder sb = new StringBuilder(word);
        StringBuilder sb2 = new StringBuilder(word);
        Iterator<AnalysisPhase> it = response.getFieldNameAnalysis("store_text")
                .getQueryPhases().iterator();
        while(it.hasNext()) {
            AnalysisPhase pharse = (AnalysisPhase)it.next();
            List<AnalysisResponseBase.TokenInfo> list = pharse.getTokens();
            for (AnalysisResponseBase.TokenInfo info : list) {
                if(ik.equals(info.getText())){//使用最大匹配度分词
                    return ik;//直接返回该词，因为solr配置schema.xml 改版本为1.1，需要手动拼接空格才能实现分词 1.5则不用
                }
                if("CN_WORD".equals(info.getType())||"ARABIC".equals(info.getType())||"ENGLISH".equals(info.getType())){//是词才拼接，字不拼接
                    sb2.append(info.getText()+" ");
                }
                sb.append(info.getText()+" ");//默认
            }

        }
        return word.equals(sb2.toString())?sb.toString():sb2.toString();
    }
    //全词和单个词的组成可以带*  多词拼接则不能
    @Deprecated
    public static String getWordByIk(String ik,String word){
        if(word==null||"".equals(word.trim())){
            return "";
        }
        //全词则没空格
        if(!ik.contains(" ")){
            return "*"+word+"*";
        }
        //单词根据“ ”开切割
        String[] ss = ik.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if(i!=0){
                String s = ss[i];
                if(s.length()>=2){//是多词 不用拼接*
                    return word;
                }
            }
        }
        return "*"+word+"*";
    }
    //根据boolean返回int值
    public static int getIntByBoolean(boolean value){
        return value?1:0;
    }
    //solr搜索智能提示Suggest
    public static List<String> getSuggestedWords(SolrServer server,String word) {
        SolrQuery params = new SolrQuery();
        params.set("qt", SUGGEST);
        params.set("q", word);
        params.set("spellcheck.build", "true");
        QueryResponse response = null;

        try {
            response = server.query(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = null;
        SpellCheckResponse spellCheckResponse = response
                .getSpellCheckResponse();
        if (spellCheckResponse != null) {
            List<SpellCheckResponse.Suggestion> suggestionList = spellCheckResponse
                    .getSuggestions();
            for (SpellCheckResponse.Suggestion suggestion : suggestionList) {
                list = suggestion.getAlternatives();
            }
        }
        if(list==null){
            list = new ArrayList<>();
        }
        return list;
    }
    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     * @param cardNum 待检验的原始卡号
     * @return 返回是否包含
     */
    public static boolean hasStr(String cardNum) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

    /**
     * 判断一个字符串是否含有数字
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) flag = true;
        return flag;

    }
}
