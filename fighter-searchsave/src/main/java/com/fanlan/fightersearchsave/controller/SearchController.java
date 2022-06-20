package com.fanlan.fightersearchsave.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author lenovo
 */
@RestController
public class SearchController {

    @Resource
    RedisTemplate redisTemplate;


    private static final String record = "redis.record.";

    private static final String getUserid = "1";

    @GetMapping("/saveRecord")
    public List<String> saveRecord() {
        List<String> searchList = new ArrayList<>();
        /** 这里拿到用户的唯一ID作为KEY来给到Redis **/
        int userId = Integer.parseInt(getUserid);
        String key = record + userId;
        long start = 1;             // 指定开始区间值
        long size = 10;            // 指定长度区间值 （查询搜索历史记录最新的10条）
        Set<ZSetOperations.TypedTuple> scoreWithScores = redisTemplate.opsForZSet()
                .reverseRangeWithScores(key, start-1, size-1);
        Iterator<ZSetOperations.TypedTuple> iterator = scoreWithScores.iterator();
        BigDecimal bigDecimal = null;
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple next = iterator.next();
            bigDecimal = BigDecimal.valueOf(next.getScore());
            //System.out.println("==》ID： "+next.getValue()+" 时间： "+bigDecimal.toPlainString());
            if ( next.getValue() != null ){
                searchList.add(next.getValue().toString());
            }
        }
        // 这里返回List给到前端
        return searchList;
    }
}
