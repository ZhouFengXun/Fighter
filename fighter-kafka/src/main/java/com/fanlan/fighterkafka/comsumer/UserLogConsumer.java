package com.fanlan.fighterkafka.comsumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author lenovo
 */
@Component
@Slf4j
public class UserLogConsumer {

    @KafkaListener(topics = {"user-log"})
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.out.println("消费消息:"+message);
        }
    }
}