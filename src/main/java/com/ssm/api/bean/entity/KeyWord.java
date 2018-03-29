package com.ssm.api.bean.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * Created by 非洲小黑 on 2017/11/29.
 */
public class KeyWord {
    //关键词
    @Field
    private String kw;
    //拼音多字段
    @Field
    private List<String> pinyin;
    //缩写多字段
    @Field
    private List<String> abbre;
    //关键词频率
    @Field
    private Integer kwfreq;

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public List<String> getPinyin() {
        return pinyin;
    }

    public void setPinyin(List<String> pinyin) {
        this.pinyin = pinyin;
    }

    public List<String> getAbbre() {
        return abbre;
    }

    public void setAbbre(List<String> abbre) {
        this.abbre = abbre;
    }

    public Integer getKwfreq() {
        return kwfreq;
    }

    public void setKwfreq(Integer kwfreq) {
        this.kwfreq = kwfreq;
    }
}
