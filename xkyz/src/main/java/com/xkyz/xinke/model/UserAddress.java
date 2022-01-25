package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("地址实体类")
@Data
@Builder
@Table(name = "user_address")
public class UserAddress {
    public UserAddress(Integer addressId, String province, String city, String district, String addressDetail, String openId) {
        this.addressId = addressId;
        this.province = province;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
        this.openId = openId;
    }

    public UserAddress() {
    }

    @ApiModelProperty("地址id")
    @Id
    private Integer addressId;
    @ApiModelProperty("省")
    public String province;
    @ApiModelProperty("市")
    public String city;
    @ApiModelProperty("区")
    public String district;
    @ApiModelProperty("详细地址")
    public String addressDetail;
    @ApiModelProperty("用户id")
    public String openId;
}
//    create table user_address(
//        address_id int primary key AUTO_INCREMENT,
//        province varchar(100) DEFAULT NULL,
//    city varchar(100) DEFAULT NULL,
//    district varchar(100) DEFAULT NULL,
//    address_detail varchar(200) DEFAULT NULL,
//    user_id int DEFAULT NULL
//            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;