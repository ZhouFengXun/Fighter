package com.fanlan.fighterspringtask.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 订单超时取消并解锁库存的定时器
 */
@Component
public class OrderTimeOutCancelTask {

    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描超时时间*2时间内所下订单，如果没支付则取消该订单
     *
     * 秒 分 小时 月份中的日期 月份 星期中的日期 年份
     */
    //@Scheduled(cron = "0 0/1 * ? * ?")
    @Scheduled(cron = " 0/10 * * * * ?")
    private void cancelTimeOutOrder(){
        System.out.println("-------------------------------");
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }

}
