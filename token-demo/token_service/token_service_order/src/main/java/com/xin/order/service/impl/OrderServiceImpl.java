package com.xin.order.service.impl;

import com.xin.order.dao.OrderMapper;
import com.xin.order.pojo.Order;
import com.xin.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }
}
