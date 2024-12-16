package com.xin.stock.listener;

import com.xin.stock.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// @Component
public class DemoListener {

    @RabbitListener(queues = RabbitMQConfig.REDUCE_STOCK_QUEUE)
    public void receiveMessage(String message){

        System.out.println(message);

        int i=1/0;
    }
}
