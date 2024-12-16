package com.xin.stock.service.impl;

import com.xin.mq.util.IdWorker;
import com.xin.stock.dao.StockFlowMapper;
import com.xin.stock.dao.StockMapper;
import com.xin.stock.pojo.StockFlow;
import com.xin.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int reduceStock(String goodsId,String messageId) {

        StockFlow stockFlow = new StockFlow();
        stockFlow.setId(String.valueOf(idWorker.nextId()));
        stockFlow.setNum(1);
        stockFlow.setGoodsId(goodsId);
        stockFlow.setFlag(messageId);
        stockFlowMapper.insert(stockFlow);
        return stockMapper.reduceStock(goodsId,1);

    }
}
