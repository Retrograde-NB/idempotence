package com.xin.stock.service;

import com.xin.stock.pojo.Stock;

public interface StockService {

    int reduceStock(String goodsId,Integer num,Integer version);

    int reduceStockNoLock(String goodsId,Integer num);

    Stock getStockInfo(String goodsId);
}
