package com.fanlan.fighterrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConifg {

    public static final String EXCHANGE_NAME="topic_exchange";
    public static final String QUEUE_NAME="topic_queue";

    @Bean("topicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean("topicQueue")
    public Queue topicQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding BindingExchangeAndQueue(@Qualifier("topicExchange") Exchange exchange,@Qualifier("topicQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("top.*").noargs();
    }
}
