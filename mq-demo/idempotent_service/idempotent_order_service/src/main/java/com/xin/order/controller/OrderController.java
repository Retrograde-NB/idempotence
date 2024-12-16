package com.xin.order.controller;

import com.xin.mq.util.IdWorker;
import com.xin.order.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/demo")
    public String demo(){

        rabbitTemplate.convertAndSend(RabbitMQConfig.REDUCE_STOCK_QUEUE,"123");

        return "success";
    }

    /**
     * 此处为了方便演示，不做基础添加数据库操作
     * @return
     */
    @PostMapping("/addOrder")
    public String addOrder(){
        String uniqueKey = String.valueOf(idWorker.nextId());
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(uniqueKey);
        messageProperties.setContentType("text/plain");
        messageProperties.setContentEncoding("utf8");
        Message message = new Message("1".getBytes(),messageProperties);
        rabbitTemplate.convertAndSend(RabbitMQConfig.REDUCE_STOCK_QUEUE,message);
        return "success";
    }

}
