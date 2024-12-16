package com.xin.order.controller;

import com.alibaba.fastjson.JSON;
import com.xin.optimistic.annotation.Idemptent;
import com.xin.optimistic.util.IdWorker;
import com.xin.order.pojo.Order;
import com.xin.order.pojo.OrderDetail;
import com.xin.order.service.OrderDetailService;
import com.xin.order.service.OrderService;
import com.xin.stock.feign.StockFeign;
import com.xin.stock.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockFeign stockFeign;

    /**
     * 重试测试
     */
    @GetMapping("/demo")
    public void demo(){
        stockFeign.demo();
    }

    /**
     * 获取令牌
     * @return
     */
    @GetMapping("/genToken")
    public String genToken(){

        String token = String.valueOf(idWorker.nextId());

        redisTemplate.opsForValue().set(token,0,30, TimeUnit.MINUTES);

        return token;
    }



    /**
     * 生成订单
     */
    @Idemptent
    @PostMapping("/genOrder")
    public String genOrder(@RequestBody Order order){
        String orderId = String.valueOf(idWorker.nextId());
        order.setId(orderId);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int result = orderService.addOrder(order);
        if (result != 1){
            System.out.println("fail");
            return "fail";
        }
        //生成订单详情信息
        List<String> goodsIdArray = JSON.parseArray(order.getGoodsIds(), String.class);
        goodsIdArray.stream().forEach(goodsId->{
            //插入订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(String.valueOf(idWorker.nextId()));
            orderDetail.setGoodsId(goodsId);
            orderDetail.setOrderId(orderId);
            orderDetail.setGoodsName("xin");
            orderDetail.setGoodsNum(1);
            orderDetail.setGoodsPrice(1);
            orderDetailService.addOrderDetail(orderDetail);
            //扣减库存(不考虑锁)
            // stockFeign.reduceStockNoLock(goodsId, orderDetail.getGoodsNum());
            Stock stockInfo = stockFeign.getStockInfo(goodsId);
            stockFeign.reduceStock(goodsId, orderDetail.getGoodsNum(), stockInfo.getVersion());
        });
        return "success";
    }
}
