package com.ssm.api.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements InitializingBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;

    @Override
    public void afterPropertiesSet() throws Exception {
        valueOperations = stringRedisTemplate.opsForValue();
    }

    public void set(String key, String value){
        valueOperations.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit){
        valueOperations.set(key, value, timeout, unit);
    }

    public String get(String key){
        return valueOperations.get(key);
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void delete(String key){
        stringRedisTemplate.delete(key);
    }
}

