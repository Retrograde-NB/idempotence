package com.xin.order.feign;

import com.xin.order.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "order")
@RequestMapping("/order")
public interface OrderFeign {
    @GetMapping("/genToken")
    public String genToken();

    @PostMapping("/genOrder")
    public String genOrder(@RequestBody Order order);

    @PostMapping("/genOrder2")
    public String genOrder2(@RequestBody Order order);
}
