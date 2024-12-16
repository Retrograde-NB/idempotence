package com.xin.stock.listener;

import com.xin.stock.config.RabbitMQConfig;
import com.xin.stock.service.StockFlowService;
import com.xin.stock.service.StockService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Random;

/**
 * @author xinlei
 * @date 2024/12/16
 */
@Component
public class ReduceStockListener {
    @Autowired
    private StockService stockService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private StockFlowService stockFlowService;

    @RabbitListener(queues = RabbitMQConfig.REDUCE_STOCK_QUEUE)
    @Transactional
    public void receiveMessage(Message message) {
        // 获取消息id
        String messageId = message.getMessageProperties().getMessageId();
        Jedis jedis = jedisPool.getResource();
        System.out.println(messageId);
        try {
            // redis锁去重校验
            if (!"OK".equals(jedis.set(messageId, messageId, "NX", "PX", 300000))) {
                System.out.println("重复请求");
                return;
            }
            // mysql状态校验
            if (!(stockFlowService.findByFlag(messageId).size() == 0)) {
                System.out.println("数据已处理");
                return;
            }
            String goodsId = null;
            try {
                // 获取消息体中goodsId
                goodsId = new String(message.getBody(), "utf-8");
                stockService.reduceStock(goodsId, messageId)
                ;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int nextInt = new Random().nextInt(100);
            System.out.println("随机数：" + nextInt);
            if (nextInt % 2 == 0) {
                int i = 1 / 0;
            }
        } catch (RuntimeException e) {
            // 解锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            jedis.eval(script, Collections.singletonList(messageId), Collections.singletonList(messageId));
            System.out.println("出现异常了");
            System.out.println(messageId + "：释放锁");
            throw e;
        }
    }
}
