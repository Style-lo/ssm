package com.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.ssm.api.service.solrImpl.SolrServiceImpl;

/**
 * 测试方法
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/spring-mvc.xml"})
public class MyTest extends AbstractTestNGSpringContextTests {
	
	@Autowired SolrServiceImpl solrServiceImpl;
    @Test
    public void test() throws Exception  {
    	//solrServiceImpl.updateGoods(2);
    }
}

