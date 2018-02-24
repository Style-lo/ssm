package com.ssm.api.extension;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OneTask {

	/**
	 * <!-- 只要在SpringMvc里面配置这个定时任务就可以使用@Component注解 -->
    <task:annotation-driven scheduler="qbScheduler" mode="proxy"/>  
    <task:scheduler id="qbScheduler" pool-size="10"/> 
	 */
	int i=0;
	@Scheduled(fixedDelay = 30 * 60 * 1000)
    public void auditSalary() {
		i++;
    	System.out.println("注解方式自动执行----"+i);
    }
}
