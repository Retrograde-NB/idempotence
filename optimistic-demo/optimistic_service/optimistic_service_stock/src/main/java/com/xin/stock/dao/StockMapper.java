package com.xin.stock.dao;

import com.xin.stock.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<Stock> {

    @Update("update tb_stock set amount=amount-#{num} where goods_id=#{goodsId}")
    int lessInventory(@Param("goodsId") String goodsId, @Param("num") int num);


    @Update("update tb_stock set amount=amount-#{num},version=version+1 where goods_id=#{goodsId} and version=#{version}")
    int lessInventoryByVersion(@Param("goodsId") String goodsId, @Param("num") int num, @Param("version") int version);

    @Update("update tb_stock set amount=amount-#{num} where goods_id=#{goodsId} and amount-#{num}>=0")
    int lessInventoryByVersionOut(@Param("goodsId") String goodsId, @Param("num") int num);
}
