package com.xkyz.xinke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("地址创建参数类")
@Data
@Builder
@Table(name = "user_address")
public class UserInsertAddressDTO {
    public UserInsertAddressDTO() {
    }

    public UserInsertAddressDTO(String province, String userName, String phoneNumber, String city, String district, String addressDetail, String skey) {
        this.province = province;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
        this.skey = skey;
    }

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
