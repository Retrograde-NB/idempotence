package com.xin.order.service.impl;

import com.xin.order.dao.OrderDetailMapper;
import com.xin.order.pojo.OrderDetail;
import com.xin.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public int addOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insert(orderDetail);
    }
}
