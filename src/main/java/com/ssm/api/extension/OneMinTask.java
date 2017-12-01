package com.ssm.api.extension;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OneMinTask implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("黄金分割线--------------------------------------------------");
	}
	int i = 0;
	
	@Scheduled(fixedDelay = 3 * 1000)
	public void task(){
		i++;
		System.out.println("第——"+i+"——次执行");
		
	}
}
