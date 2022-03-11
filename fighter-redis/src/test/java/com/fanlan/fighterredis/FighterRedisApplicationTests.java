package com.fanlan.fighterredis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FighterRedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void setname() throws InterruptedException {

        String name="USERKEY";
        redisTemplate.boundValueOps(name).set("zhangsan");
        Object key = redisTemplate.boundValueOps(name).get();
        redisTemplate.boundValueOps(name).expire(1, TimeUnit.SECONDS);
        System.out.println(key);
        Thread.sleep(10000);
        Object o = redisTemplate.boundValueOps(name).get();
        System.out.println(o);

    }

    @Test
    public void dome(){
        redisTemplate.boundValueOps("key").set("asd",1,TimeUnit.MINUTES);
    }

    @Test
    public void dome1(){
        long l = System.currentTimeMillis();
        System.out.println(l);
    }
}
