package com.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
/**
 * Created by 非洲小黑 on 2018/2/2.
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/spring-mvc.xml" })
public class MyTest extends AbstractTestNGSpringContextTests {
	
	
    @Test
    public void test() throws Exception  {
    }
}

