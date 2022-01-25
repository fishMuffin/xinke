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

    public DeliverTask(Integer taskId, String deliverOpenId, Integer expressAmount, String storePhone, String storeName, Integer status, String expressCompany, String ownerOpenId, String orderNo, Double price, Integer weight, String imageUrl) {
        this.taskId = taskId;
        this.deliverOpenId = deliverOpenId;
        this.expressAmount = expressAmount;
        this.storePhone = storePhone;
        this.storeName = storeName;
        this.status = status;
        this.expressCompany = expressCompany;
        this.ownerOpenId = ownerOpenId;
        this.orderNo = orderNo;
        this.price = price;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    @ApiModelProperty("揽收任务id")
    private Integer taskId;
    @ApiModelProperty("揽收员OpenId")
    private String deliverOpenId;
    @ApiModelProperty("快递数量")
    private Integer expressAmount;
    @ApiModelProperty("商家电话")
    private String storePhone;
    @ApiModelProperty("商家名称")
    private String storeName;
    @ApiModelProperty("揽收状态：1未揽收，2已揽收，3新任务")
    private Integer status;
    @ApiModelProperty("快递公司")
    private String expressCompany;
    @ApiModelProperty("快递主人OpenId")
    private String ownerOpenId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("价格")
    private Double price;
    @ApiModelProperty("重量")
    private Integer weight;
    @ApiModelProperty("图片URL")
    private String imageUrl;
//    create table deliver_task
//            (
//                    task_id         int primary key AUTO_INCREMENT,
//                    deliver_open_id varchar(40)  DEFAULT NULL,
//    express_amount  int    NOT NULL,
//    store_phone     varchar(40)  DEFAULT NULL,
//    store_address   varchar(100) DEFAULT NULL,
//    store_name      varchar(40)  DEFAULT NULL,
//    express_company varchar(40)  DEFAULT NULL,
//    owner_open_id   varchar(40)  DEFAULT NULL,
//    order_no        varchar(100) DEFAULT NULL,
//    price           DECIMAL(10, 2),
//    weight          int    NOT NULL,
//    image_url       varchar(100) DEFAULT NULL,
//    status          int(3) NOT NULL
//
//) ENGINE = InnoDB
//    DEFAULT CHARSET = utf8mb3;
}
