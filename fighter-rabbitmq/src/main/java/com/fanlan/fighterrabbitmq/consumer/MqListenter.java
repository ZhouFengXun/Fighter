package com.fanlan.fighterrabbitmq.consumer;

import com.fanlan.fighterrabbitmq.config.RabbitConifg;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConifg.QUEUE_NAME)
public class MqListenter implements ChannelAwareMessageListener{
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Thread.sleep(1000);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //接口转换消息
            System.out.println(new String(message.getBody()));
            //实现业务逻辑
            System.out.println("实现业务逻辑");
            //手动签收 true 签收多条消息

            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            // 第三个参数 含义是重回队发送
            channel.basicNack(deliveryTag,true,true);
        }
    }
}
