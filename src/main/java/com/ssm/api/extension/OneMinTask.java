package com.ssm.api.extension;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OneMinTask implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}
	int i = 0;
	/**
	 * 半小时执行一次
	 */
	@Scheduled(fixedDelay = 30 * 60 * 1000)
	public void task(){
		i++;
		System.out.println("第——"+i+"——次执行");
		
	}
}
