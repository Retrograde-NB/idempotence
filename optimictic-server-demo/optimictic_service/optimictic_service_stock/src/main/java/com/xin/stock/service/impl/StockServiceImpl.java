package com.xin.stock.service.impl;

import com.xin.stock.dao.StockMapper;
import com.xin.stock.pojo.Stock;
import com.xin.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reduceStock(String goodsId, Integer num, Integer version) {
        return stockMapper.reduceStock(goodsId, num, version);
    }

    @Override
    @Transactional
    public int reduceStockNoLock(String goodsId, Integer num) {
        return stockMapper.reduceStockNoLock(goodsId,num);
    }

    @Override
    public Stock getStockInfo(String goodsId) {
        Stock stock = stockMapper.findByGoodsId(goodsId);
        return stock;
    }
}
