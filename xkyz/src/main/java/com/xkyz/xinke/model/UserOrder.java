package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Builder;
import lombok.Data;

@ApiModel("订单实体类")
@Data
@Builder
public class UserOrder {

    public UserOrder(Integer orderId, String orderNo, String stuffType, Integer sendAddress, Integer receiveAddress, Integer status, String orderTime, String openId, Double price) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.stuffType = stuffType;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
        this.status = status;
        this.orderTime = orderTime;
        this.openId = openId;
        this.price = price;
    }

    public UserOrder() {
    }

    @ApiModelProperty("订单ID")
    private Integer orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("物品类型")
    private String stuffType;
    @ApiModelProperty("寄件地址")
    private Integer sendAddress;
    @ApiModelProperty("收件地址")
    private Integer receiveAddress;
    @ApiModelProperty("订单状态：1未付款，2已付款未发货，3已发货未确认，4已确认未评价，5交易关闭，6交易成功，已评价")
//    情况，一般分为四种：新订单、教材配送中、已完成、已取消。 新订单：订单成功提交但未付款的订单状态； 教材配送中：用户已付款，等待收货的订单状态 ； 已完成：交易成功的订单状态； 已取消：订单成功提交但7天内未付款的订单状态。
    private Integer status;
    @ApiModelProperty("下单时间")
    private String orderTime;
    @ApiModelProperty("openID")
    private String openId;
    @ApiModelProperty("订单金额")
    private Double price;
//    create table user_order
//            (
//                    order_id        int primary key AUTO_INCREMENT,
//                    order_no        varchar(100) DEFAULT NULL,
//    stuff_type      varchar(100) DEFAULT NULL,
//    send_address    varchar(200) DEFAULT NULL,
//    status          int(3) NOT NULL,
//    receive_address varchar(200) DEFAULT NULL,
//    order_time      varchar(100) DEFAULT NULL
//) ENGINE = InnoDB
//    DEFAULT CHARSET = utf8mb3;
}
