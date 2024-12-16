package com.xin.order.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    private String id;//订单id

    private Integer totalNum;//数量合计
    private Integer payMoney;//实付金额
    private String payType;//支付类型，1、在线支付、0 货到付款
    private Date payTime;//付款时间

    private String receiverContact;//收货人
    private String receiverMobile;//收货人手机
    private String receiverAddress;//收货人地址

    private Date createTime;//订单创建时间
    private Date updateTime;//订单更新时间


}
