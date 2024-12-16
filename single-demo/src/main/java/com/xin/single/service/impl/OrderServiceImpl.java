package com.xin.single.service.impl;

import com.xin.single.dao.OrderMapper;
import com.xin.single.pojo.Order;
import com.xin.single.service.OrderService;
import com.xin.single.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderMapper orderMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String addOrder(Order order) {
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //查询
        Order orderResult = orderMapper.selectByPrimaryKey(order.getId());
        Optional<Order> orderOptional = Optional.ofNullable(orderResult);
        if (orderOptional.isPresent()){
            return "repeat request";
        }
        int result = orderMapper.insert(order);
        if (result != 1){
            return "fail";
        }
        return "success";
    }
}
