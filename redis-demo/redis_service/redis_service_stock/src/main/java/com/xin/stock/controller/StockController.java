package com.xin.stock.controller;

import com.alibaba.fastjson.JSON;
import com.xin.order.pojo.OrderDetail;
import com.xin.stock.pojo.Stock;
import com.xin.stock.service.StockFlowService;
import com.xin.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StockFlowService stockFlowService;


    /**
     * 扣减库存
     * @param orderListValue
     * @param flag
     * @throws InterruptedException
     */
    @PutMapping("/reduceStock/{flag}")
    public void reduceStock(@RequestParam String orderListValue, @PathVariable("flag") String flag) throws InterruptedException {

        System.out.println("reduce stock");

        //redis验重
        if (!redisTemplate.delete(flag)){
            System.out.println("redis验重 重复操作");
            return;
        }

        //MYSQL验重
        int dbResult = stockFlowService.findByFlag(flag).size();
        if (dbResult >0){
            System.out.println("mysql验重 重复操作");
            return;
        }

        //扣减库存
        List<OrderDetail> orderDetailList = JSON.parseArray(orderListValue, OrderDetail.class);
        stockService.reduceStock(orderDetailList,flag);

        TimeUnit.SECONDS.sleep(6);

    }

    /**
     * 获取库存信息
     * @param goodsId
     * @return
     */
    @GetMapping("/getStockInfo/{goodsId}")
    public Stock getStockInfo(@PathVariable("goodsId") String goodsId){

        return stockService.getStockInfo(goodsId);
    }


}
