package com.ssm.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.api.service.RedisService;
import com.ssm.api.utils.JsonMapper;

@Api(value = "/redis", tags = "redis相关接口")
@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "set", method = RequestMethod.POST)
    @ApiOperation(value = "向redis插入数据")
    public void set(@RequestBody String body){
    	Map<String, Object> stringToMap = JsonMapper.stringToMap(body);
    	//stringToMap.put("timeout", 30);
    	stringToMap.put("key", "litest");
    	stringToMap.put("value", "testOutTime");
    	Long timeout=1l;
        if(timeout == null || timeout <= 0){
            redisService.set(stringToMap.get("key").toString(), stringToMap.get("value").toString());
        } else {
        	try {
        		redisService.set(stringToMap.get("key").toString(), stringToMap.get("value").toString(), timeout, TimeUnit.MINUTES);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除数据")
    public void delete(@RequestParam String key){
        redisService.delete(key);
    }
}
