package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("附近网店实体类")
@Data
@Builder
@Table(name = "store_network")
public class StorePoints {
    public StorePoints() {
    }

    public StorePoints(Integer id, String pointName, Integer pointType, String pointPhone, String city, String district, String addressDetail, Double latitude, Double longitude, Double distanceFromHere) {
        this.id = id;
        this.pointName = pointName;
        this.pointType = pointType;
        this.pointPhone = pointPhone;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFromHere = distanceFromHere;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    @ApiModelProperty("网店名称")
    public String pointName;
    @ApiModelProperty("网点类型：1--快递超市，2--菜鸟驿站，3--xxxx")
    public Integer pointType;
    @ApiModelProperty("网点电话")
    public String pointPhone;
    @ApiModelProperty("市")
    public String city;
    @ApiModelProperty("区")
    public String district;
    @ApiModelProperty("详细地址")
    public String addressDetail;
    @ApiModelProperty("纬度")
    public Double latitude;
    @ApiModelProperty("经度")
    public Double longitude;
    @ApiModelProperty("距离")
    public Double distanceFromHere;
}
//    CREATE TABLE `store_network`
//        (
//        `id`           int         NOT NULL AUTO_INCREMENT,
//        `point_name` varchar(100) DEFAULT NULL,
//        `point_type`   int DEFAULT NULL,
//        `point_phone`  varchar(40) NOT NULL,
//        `city`           varchar(100) DEFAULT NULL,
//        `district`       varchar(100) DEFAULT NULL,
//        `address_detail` varchar(200) DEFAULT NULL,
//        `distance_from_here` int DEFAULT NULL,
//        `latitude` decimal(10,7) DEFAULT NULL comment '经度',
//        `longitude` decimal(10,7) DEFAULT NULL comment '纬度',
//        PRIMARY KEY (`id`)
//        ) ENGINE = InnoDB
//        DEFAULT CHARSET = utf8mb3;
