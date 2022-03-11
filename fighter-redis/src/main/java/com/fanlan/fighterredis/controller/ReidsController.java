package com.fanlan.fighterredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
//@RequestMapping("")
public class ReidsController {

    private final Logger logger = LoggerFactory.getLogger(ReidsController.class);

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("get")
    public void Get(){
        // redis中存储key 为 Key_StringName ,value:ngsan ,过期时间 1 ms
        redisTemplate.opsForValue().set("Key_StringName","zhangsan",1L, TimeUnit.SECONDS);

        Object key_stringName = redisTemplate.opsForValue().get("Key_StringName");
        System.out.println(key_stringName);
        if (key_stringName!= null){
            System.out.println("没过期");
        }else {
            System.out.println("过期");
        }
        Long list_key = redisTemplate.opsForList().leftPush("list_key", "1");
        System.out.println(list_key);
        Object list_key1 = redisTemplate.opsForList().rightPop("list_key");
        System.out.println(list_key1);
        RedisOperations operations = redisTemplate.opsForList().getOperations();
        System.out.println(operations);
    }



}
