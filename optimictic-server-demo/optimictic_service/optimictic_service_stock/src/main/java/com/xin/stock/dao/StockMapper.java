package com.xin.stock.dao;

import com.xin.stock.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<Stock> {

    @Update("update tb_stock set amount=amount-#{num} where goods_id=#{goodsId}")
    int reduceStockNoLock(@Param("goodsId") String goodsId,@Param("num") Integer num);

    @Update("update tb_stock set version=version+1,amount=amount-#{num} where goods_id=#{goodsId} and version=#{version} and amount-#{num}>=0")
    int reduceStock(@Param("goodsId") String goodsId,@Param("num") Integer num,@Param("version") Integer version);

    @Select("select * from tb_stock where goods_id = #{goodsId}")
    Stock findByGoodsId(@Param("goodsId") String goodsId);
}
