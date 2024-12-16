package com.xin.order.controller;

import com.xin.annotation.Idemptent;
import com.xin.order.pojo.Order;
import com.xin.order.service.OrderService;
import com.xin.util.IdWorker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xinlei
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private IdWorker idWorker;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/genToken")
    public String genToken() {
        String token = String.valueOf(idWorker.nextId());
        redisTemplate.opsForValue().set(token, "0", 30, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 生成订单
     */
    @PostMapping("/genOrder")
    public String genOrder(@RequestBody Order order, HttpServletRequest request) {
        // 获取令牌
        String token = request.getHeader("token");
        // 校验令牌
        try {
            if (Boolean.TRUE.equals(redisTemplate.delete(token))) {
                // 令牌删除成功，代表不是重复请求，执行具体业务
                order.setId(String.valueOf(idWorker.nextId()));
                order.setCreateTime(new Date());
                order.setUpdateTime(new Date());
                int result = orderService.addOrder(order);
                if (result == 1) {
                    System.out.println("success");
                    return "success";
                } else {
                    System.out.println("fail");
                    return "fail";
                }
            } else {
                // 删除令牌失败，重复请求
                System.out.println("repeat request");
                return "repeat request";
            }
        } catch (Exception e) {
            throw new RuntimeException("系统异常, 请重试");
        }
    }
    @Idemptent
    @PostMapping("/genOrder2")
    public String genOrder2(@RequestBody Order order){
        order.setId(String.valueOf(idWorker.nextId()));
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int result = orderService.addOrder(order);
        if (result == 1){
            System.out.println("success");
            return "success";
        }else {
            System.out.println("fail");
            return "fail";
        }
    }

}
