package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("订单实体类")
@Data
@Builder
public class UserOrder {
    public UserOrder(Integer orderId, String orderNo, String stuffType, Integer sendAddress, Integer receiveAddress, Integer status, Integer deliverStatus, Long orderTime, Long orderUpdateTime, String userToken, String deliverToken, Integer pointsId, Double price, Double estimatedWeight, Integer expressCompanyId, String expressNo) {
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
        this.pointsId = pointsId;
        this.price = price;
        this.estimatedWeight = estimatedWeight;
        this.expressCompanyId = expressCompanyId;
        this.expressNo = expressNo;
    }

    public UserOrder() {
    }

    @ApiModelProperty("订单ID")
    private Integer orderId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("物品类型:1-日用品，2-食品，3-文件，4-衣物，5-数码产品，6-其他")
    private String stuffType;
    @ApiModelProperty("寄件地址")
    private Integer sendAddress;
    @ApiModelProperty("收件地址")
    private Integer receiveAddress;
    //    情况，一般分为四种：新订单、教材配送中、已完成、已取消。 新订单：订单成功提交但未付款的订单状态； 教材配送中：用户已付款，等待收货的订单状态 ； 已完成：交易成功的订单状态； 已取消：订单成功提交但7天内未付款的订单状态。
    @ApiModelProperty("订单状态：1未结算，2已取消，3已发货")
    private Integer status;
    @ApiModelProperty("揽收状态：1未揽收，2已揽收")
    private Integer deliverStatus;
    @ApiModelProperty("下单时间")
    private Long orderTime;
    @ApiModelProperty("订单更新时间")
    private Long orderUpdateTime;
    @ApiModelProperty("用户token")
    private String userToken;
    @ApiModelProperty("揽收员token")
    private String deliverToken;
    @ApiModelProperty("网点id")
    private Integer pointsId;
    @ApiModelProperty("订单金额")
    private Double price;
    @ApiModelProperty("预估重量,单位kg")
    private Double estimatedWeight;
    @ApiModelProperty("选择的快递公司id")
    private Integer expressCompanyId;
    @ApiModelProperty("快递单号")
    private String expressNo;
}
