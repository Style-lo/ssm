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
	//	http://blog.csdn.net/sd4000784/article/details/7745947
	@Scheduled(cron = "0 0 0 * * ?")
	public void task1(){
		System.out.println("每天0:0:0分执行，可参考上面URL");
		
	}
}
