package com.fanlan.fighterwx;

import com.alibaba.fastjson.JSONObject;
import com.fanlan.fighterwx.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

@SpringBootTest
class FighterWxApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() throws InterruptedException {
//        //Object access_token = redisUtil.get("access_token");
//        redisTemplate.opsForValue().set("name","zhangsan",1, TimeUnit.MINUTES);
//
//        Thread.sleep(10000);
//        Long name = redisTemplate.getExpire("name");
//        System.out.println(name);

        long timeMillis = System.currentTimeMillis();

        Thread.sleep(10000);

        long timeMillis1 = System.currentTimeMillis();

        System.out.println((timeMillis1-timeMillis)/1000);


    }

    private String get(Object access_token) {
        if (access_token == null || access_token.toString().isEmpty()|| access_token.equals("")) {
            return "为空";
        } else {
            return " 不为空";
        }
    }


    @Test
    public void print(){
        long timeMillis = System.currentTimeMillis();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("accessToken", "qwqeqweq");
        map1.put("expiretime", String.valueOf(timeMillis));
        redisTemplate.opsForValue().set("accessToken", map1);
        Object accessToken = redisTemplate.opsForValue().get("accessToken");
        String s = JSONObject.toJSONString(accessToken);
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        Object expiretime = jsonObject.get("expiretime");
        System.out.println(expiretime);

        //System.out.println(jsonObject.toString());
    }

    @Test
    public void print1(){

    }


}
