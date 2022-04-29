package com.fanlan.fighterrabbitmq.producer;

import com.fanlan.fighterrabbitmq.config.RabbitConifg;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class producerController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @PostMapping("sendMessage")
    public String sendMessage() throws InterruptedException {

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData  相关配置
             * @param ack              exchange 是否收到消息 true  成功  false 失败
             * @param cause            失败得原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm方法被执行了。。。。");
                if (ack){
                    System.out.println("接收成功"+cause);
                }else {
                    System.out.println("接口失败"+cause);
                }
            }
        });
        //设置交换机处理失败消息得模式
        rabbitTemplate.setMandatory(true);
        //交换机是否发送到相应得队列
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message  消息体
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange  交换机
             * @param routingKey  路由
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("return执行了。。。。。");
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
            }
        });
        rabbitTemplate.convertAndSend(RabbitConifg.EXCHANGE_NAME,"top.email","发送邮件。。。。");
        return "ok";
    }
}
