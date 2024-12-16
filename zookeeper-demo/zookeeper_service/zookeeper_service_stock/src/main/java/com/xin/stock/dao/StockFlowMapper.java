package com.xin.stock.dao;

import com.xin.stock.pojo.StockFlow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StockFlowMapper extends Mapper<StockFlow> {

    @Select("select * from tb_stock_flow where flag = #{flag}")
    List<StockFlow> findByFlag(@Param("flag") String flag);
}
