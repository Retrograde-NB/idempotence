package com.xin.stock.service;

import com.xin.stock.pojo.StockFlow;

import java.util.List;

public interface StockFlowService {

    List<StockFlow> findByFlag(String messageId);
}
