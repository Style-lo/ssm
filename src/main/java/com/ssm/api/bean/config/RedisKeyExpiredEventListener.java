package com.ssm.api.bean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RedisKeyExpiredEventListener implements ApplicationListener<RedisKeyExpiredEvent> {

    @Override
    public void onApplicationEvent(RedisKeyExpiredEvent event) {
        String message = new String(event.getSource());
        	System.out.println(message+"-------------------------");
        String entityId;
        if(message.indexOf("li") == 0){
            //订单过期处理
            entityId = message.substring("li".length());
            System.out.println("redis——————————————————————————"+entityId);
            System.out.println("redis——————————————————————————"+entityId);

        } 
    }
}

