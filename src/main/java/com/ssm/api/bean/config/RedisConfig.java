package com.ssm.api.bean.config;

import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig /*extends CachingConfigurerSupport*/ {

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public KeyExpirationEventMessageListener keyExpirationEventMessageListener(RedisMessageListenerContainer listenerContainer){
        return new KeyExpirationEventMessageListener(listenerContainer);
    }

    @Bean(name = "redisTemplateKeyString")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(target.getClass().getSimpleName());
            keyBuilder.append(":").append(method.getName()).append("[");
            for (Object param : params) {
                keyBuilder.append(param != null ? param.toString() : "null").append("-");
            }
            keyBuilder.deleteCharAt(keyBuilder.length() - 1).append(']');
            return keyBuilder.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplateKeyString) {
        RedisCacheManager manager = new RedisCacheManager(redisTemplateKeyString);
        manager.setUsePrefix(true);

        //整体缓存过期时间
        manager.setDefaultExpiration(3600L);

        //设置缓存过期时间。key和缓存过期时间，单位秒
        Map<String, Long> expiresMap = new HashMap<>();
        manager.setExpires(expiresMap);
        return manager;
    }
}
