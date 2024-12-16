package com.xin.order.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_order_detail")
public class OrderDetail {

    @Id
    private String id;

    private String orderId;

    private String goodsId;

    private String goodsName;

    private Integer goodsNum;

    private Integer goodsPrice;
}
