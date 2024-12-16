package com.xin.stock.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_stock_flow")
public class StockFlow {

    @Id
    private String id;

    private String goodsId;

    private Integer num;

    private String flag;
}
