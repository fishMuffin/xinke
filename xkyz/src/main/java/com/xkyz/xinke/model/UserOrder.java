package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("订单实体类")
@Data
@Builder
public class UserOrder {

    public UserOrder(Integer orderId, String orderNo, String stuffType, Integer sendAddress, Integer receiveAddress, Integer status, Integer deliverStatus, Long orderTime, Long orderUpdateTime, String userToken, String deliverToken, String storeToken, Double price) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.stuffType = stuffType;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
        this.status = status;
        this.deliverStatus = deliverStatus;
        this.orderTime = orderTime;
        this.orderUpdateTime = orderUpdateTime;
        this.userToken = userToken;
        this.deliverToken = deliverToken;
        this.storeToken = storeToken;
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
    @ApiModelProperty("订单状态：1未付款，2已付款，3已提现")
//    情况，一般分为四种：新订单、教材配送中、已完成、已取消。 新订单：订单成功提交但未付款的订单状态； 教材配送中：用户已付款，等待收货的订单状态 ； 已完成：交易成功的订单状态； 已取消：订单成功提交但7天内未付款的订单状态。
    private Integer status;
    @ApiModelProperty("订单状态：1.新任务，2未揽收，3已揽收")
    private Integer deliverStatus;
    @ApiModelProperty("下单时间")
    private Long orderTime;
    @ApiModelProperty("订单更新时间")
    private Long orderUpdateTime;
    @ApiModelProperty("用户token")
    private String userToken;
    @ApiModelProperty("揽收员token")
    private String deliverToken;
    @ApiModelProperty("商家token")
    private String storeToken;
    @ApiModelProperty("订单金额")
    private Double price;
}
