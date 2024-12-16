package com.xin.stock.service;

public interface StockService {

    /**
     * 扣减库存
     *
     * @param goodsId
     * @param num
     * @return
     */
    int lessInventory(String goodsId, int num);

    int lessInventoryByVersion(String goodsId, int num, int version);

    int lessInventoryByVersionOut(String goodsId, int num);
}
