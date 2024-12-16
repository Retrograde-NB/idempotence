package com.xin.stock.service;

import com.xin.order.pojo.OrderDetail;
import com.xin.stock.pojo.Stock;

import java.util.List;

public interface StockService {

    Boolean reduceStock(List<OrderDetail> orderDetailList,String flag);

    Stock getStockInfo(String goodsId);
}
