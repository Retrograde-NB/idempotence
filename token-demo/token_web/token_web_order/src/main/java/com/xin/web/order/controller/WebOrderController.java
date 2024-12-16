package com.xin.web.order.controller;

import com.xin.order.feign.OrderFeign;
import com.xin.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("worder")
public class WebOrderController {

    @Autowired
    private OrderFeign orderFeign;

    /**
     * 服务端生成token
     */
    @GetMapping("/genToken")
    public String genToken() {
        return orderFeign.genToken();
    }
    /**
     * 新增订单
     */
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order){
        return orderFeign.genOrder(order);
    }
    @PostMapping("/addOrder2")
    public String addOrder2(@RequestBody Order order){
        return orderFeign.genOrder2(order);
    }
}
