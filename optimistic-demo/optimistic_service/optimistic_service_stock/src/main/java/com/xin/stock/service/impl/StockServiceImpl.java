package com.xin.stock.service.impl;

import com.xin.stock.dao.StockMapper;
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
    public synchronized int lessInventory(String goodsId, int num) {
        return stockMapper.lessInventory(goodsId, num);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int lessInventoryByVersion(String goodsId, int num, int version) {
        return stockMapper.lessInventoryByVersion(goodsId, num, version);
    }

    @Override
    public int lessInventoryByVersionOut(String goodsId, int num) {
        return stockMapper.lessInventoryByVersionOut(goodsId, num);
    }

}
