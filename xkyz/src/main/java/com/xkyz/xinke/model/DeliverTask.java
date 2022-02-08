package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

@ApiModel("揽收员实体类")
@Data
@Builder
public class DeliverTask {

    public DeliverTask() {
    }

    public DeliverTask(Integer taskId, String deliverToken, Integer expressAmount, Integer status, Integer expressCompanyId, String ownerToken, Integer pointsId, String orderNo, Double price, Integer weight, String imageUrl) {
        this.taskId = taskId;
        this.deliverToken = deliverToken;
        this.expressAmount = expressAmount;
        this.status = status;
        this.expressCompanyId = expressCompanyId;
        this.ownerToken = ownerToken;
        this.pointsId = pointsId;
        this.orderNo = orderNo;
        this.price = price;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    @ApiModelProperty("揽收任务id")
    private Integer taskId;
    @ApiModelProperty("揽收员token")
    private String deliverToken;
    @ApiModelProperty("快递数量")
    private Integer expressAmount;
    @ApiModelProperty("揽收状态：1未揽收，2已揽收，3新任务")
    private Integer status;
    @ApiModelProperty("快递公司id")
    private Integer expressCompanyId;
    @ApiModelProperty("快递主人token")
    private String ownerToken;
    @ApiModelProperty("网点id")
    private Integer pointsId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("价格")
    private Double price;
    @ApiModelProperty("重量")
    private Integer weight;
    @ApiModelProperty("图片URL")
    private String imageUrl;
}
