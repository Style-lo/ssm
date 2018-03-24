package com.ssm.api.bean.entity;

import java.util.List;

/**
 * Created by 非洲小黑 on 2017/11/14.
 */
public class SolrResults<T> {
    //返回数
    private Long numFound;
    //开始数
    private Long start;
    //返回的集合
    private List docs;

    public Long getNumFound() {
        return numFound;
    }
    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }
    public Long getStart() {
        return start;
    }
    public void setStart(Long start) {
        this.start = start;
    }
    public List<T> getDocs() {
        return docs;
    }
    public void setDocs(List docs) {
        this.docs = docs;
    }
}
