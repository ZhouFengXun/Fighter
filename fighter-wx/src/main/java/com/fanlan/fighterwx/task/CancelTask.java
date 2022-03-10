package com.fanlan.fighterwx.task;

import com.alibaba.fastjson.JSONObject;
import com.fanlan.fighterwx.service.WeChatService;
import com.fanlan.fighterwx.service.impl.WeChatServiceImpl;
import com.fanlan.fighterwx.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时刷新AccessToken
 */
@Component
public class CancelTask {

    @Autowired
    WeChatService weChatService;

    @Autowired
    RedisUtil redisUtil;

    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描超时时间*2时间内所下订单，如果没支付则取消该订单
     * <p>
     * 秒 分 小时 月份中的日期 月份 星期中的日期 年份
     */
    //@Scheduled(cron = "0 0/1 * ? * ?")
    //@Scheduled(cron = " 0/10 * * * * ?")
    private void cancelAccessToken() throws Exception {
        logger.info("---------------定时刷新AccessToken----------------");
        String accessToken = weChatService.getAccessToken();
        JSONObject jsonObject = JSONObject.parseObject(accessToken);
        logger.info("AccessToken:{}",jsonObject.getString("access_token"));
        logger.info("---------------刷新AccessToken完成--------------------");
    }
//    @PostConstruct
//    @Scheduled(cron = "0 0/1 * ? * ?")
//    //@Scheduled(cron = " 0/10 * * * * ?")
//    private void IsEffectiveAccessToken() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        logger.info("---------------定时检查AccessToken是否有效----------------");
//        boolean expireAccessToken = weChatService.isExpireAccessToken();
//        if (Boolean.valueOf(expireAccessToken)==true){
//            System.out.println("access_token 在有效期内，不需要重新获取");
//        }else{
//            String accessToken = weChatService.getAccessToken();
//            JSONObject jsonObject = JSONObject.parseObject(accessToken);
//            logger.info("AccessToken:{}",jsonObject.getString("access_token"));
//            System.out.println("access_token 不在有效期内，需要重新获取");
//        }
//        logger.info("---------------定时检查AccessToken是否有效--------------------");
//    }
}
