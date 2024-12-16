package com.xin.stock.service.impl;

import com.xin.stock.dao.StockFlowMapper;
import com.xin.stock.pojo.StockFlow;
import com.xin.stock.service.StockFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockFlowServiceImpl implements StockFlowService {

    @Autowired
    private StockFlowMapper stockFlowMapper;

    @Override
    public List<StockFlow> findByFlag(String flag) {

        return stockFlowMapper.findByFlag(flag);
    }
}
