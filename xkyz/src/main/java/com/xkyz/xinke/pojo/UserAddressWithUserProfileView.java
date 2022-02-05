package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.UserAddress;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAddressWithUserProfileView {
    public UserAddressWithUserProfileView() {
    }

    public UserAddressWithUserProfileView(String wx_name, String phone_number, Integer addressId, String province, String city, String district, String addressDetail) {
        this.wx_name = wx_name;
        this.phone_number = phone_number;
        this.addressId = addressId;
        this.province = province;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
    }

    public String wx_name;
    public String phone_number;
    private Integer addressId;
    public String province;
    public String city;
    public String district;
    public String addressDetail;
}
