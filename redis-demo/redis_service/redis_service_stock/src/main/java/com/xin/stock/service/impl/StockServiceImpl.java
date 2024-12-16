package com.xin.stock.service.impl;

import com.xin.order.pojo.OrderDetail;
import com.xin.stock.dao.StockFlowMapper;
import com.xin.stock.dao.StockMapper;
import com.xin.stock.pojo.Stock;
import com.xin.stock.service.StockService;
import com.xin.redis.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockFlowMapper stockFlowMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduceStock(List<OrderDetail> orderDetailList, String flag) {

        return null;
    }

    @Override
    public Stock getStockInfo(String goodsId) {
        Stock stock = stockMapper.findByGoodsId(goodsId);
        return stock;
    }
}
