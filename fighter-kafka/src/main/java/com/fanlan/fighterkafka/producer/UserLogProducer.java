package com.fanlan.fighterkafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private final static String TOPIC_NAME="user-log";
    /**
     * 发送数据
     */
    public void sendLog(){
        System.err.println("发送用户日志数据");
        kafkaTemplate.send(TOPIC_NAME,"{name:'zhangsan',age:'12',statu:'0'}");
    }
}