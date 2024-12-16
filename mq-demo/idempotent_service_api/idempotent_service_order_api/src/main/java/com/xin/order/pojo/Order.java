package com.xin.order.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_order")
public class Order {

    @Id
    private String id;

    private Integer totalNum;

    private Integer payMoney;

    private java.util.Date createTime;
    private java.util.Date updateTime;

    private String goodsIds;
}
