package com.xin.web.order.controller;

import com.xin.order.feign.OrderFeifn;
import com.xin.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worder")
public class WebOrderController {

    @Autowired
    private OrderFeifn orderFeifn;

    @GetMapping("/genToken")
    public String genToken(){
        return orderFeifn.genToken();
    }

    @PostMapping("/genOrder")
    public String genToken(@RequestBody Order order){
        return orderFeifn.genOrder(order);
    }
}
