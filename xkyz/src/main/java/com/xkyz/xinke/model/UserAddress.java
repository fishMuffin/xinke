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
    public UserAddress() {
    }

    public UserAddress(Integer addressId, String province, String userName, String phoneNumber, String city, String district, String addressDetail, String skey) {
        this.addressId = addressId;
        this.province = province;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
        this.skey = skey;
    }

    @ApiModelProperty("地址id")
    @Id
    private Integer addressId;
    @ApiModelProperty("省")
    public String province;
    @ApiModelProperty("用户姓名")
    public String userName;
    @ApiModelProperty("用户电话号码")
    public String phoneNumber;
    @ApiModelProperty("市")
    public String city;
    @ApiModelProperty("区")
    public String district;
    @ApiModelProperty("详细地址")
    public String addressDetail;
    @ApiModelProperty("用户token")
    public String skey;
}
