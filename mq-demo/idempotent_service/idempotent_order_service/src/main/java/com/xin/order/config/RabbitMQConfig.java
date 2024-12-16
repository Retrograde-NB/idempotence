package com.xin.order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String REDUCE_STOCK_QUEUE="reduce_stock";

    @Bean
    public Queue queue(){
        return new Queue(REDUCE_STOCK_QUEUE,true);
    }
}
