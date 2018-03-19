package com.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/spring-mvc.xml" })
public class Testing extends AbstractTestNGSpringContextTests{

	
    @Test
    public void test()  throws Exception {
    	
    }
}
