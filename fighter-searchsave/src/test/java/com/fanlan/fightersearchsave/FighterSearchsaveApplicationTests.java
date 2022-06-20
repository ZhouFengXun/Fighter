package com.fanlan.fightersearchsave;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class FighterSearchsaveApplicationTests {


    @Resource
    RedisTemplate redisTemplate;


    @Test
    public void saveRecord() {
        String getUserid = "1";
        String record = "redis.record.";

        String companyName="游戏1";
        int userId = Integer.parseInt(getUserid);      // 拿到用户ID
        // 把用户ID当key，搜索内容当value 存入 Redis
        redisTemplate.opsForZSet().add(record + userId, companyName, System.currentTimeMillis());
        // 调用下面的方法对存入Redis的数据进行处理
        //insertSearchSort(record + userId, companyName);
    }

    /**
     * 对传进来的搜索内容进行判断
     * @param key
     * @param value
     */
    public void insertSearchSort(String key,String value){
        //阈值-历史最多10个
        long top  = 10;
        // 拿到存入Redis里数据的唯一分值
        Double score = redisTemplate.opsForZSet().score(key, value);
        //检索是否有旧记录  1.无则插入记录值  2.有则删除 再次插入
        if(null != score){
            //删除旧的
            redisTemplate.opsForZSet().remove(key,value);
        }
        //加入新的记录，设置当前时间戳为分数score
        redisTemplate.opsForZSet().add(key,value,System.currentTimeMillis());
        //获取总记录数
        Long aLong = redisTemplate.opsForZSet().zCard(key);
        if(aLong > top){
            //获取阈值200之后的记录  (0,1] 并移除
            redisTemplate.opsForZSet().removeRange(key,0,aLong-top-1);
        }
    }
}
