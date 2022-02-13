package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderView {


    public UserOrderView(UserAddress sendAddress, UserOrder userOrder, UserAddress receiveAddress) {
        this.sendAddress = sendAddress;
        this.userOrder = userOrder;
        this.receiveAddress = receiveAddress;
    }

    public UserOrderView() {
    }


    private UserAddress sendAddress;
    private UserOrder userOrder;
    private UserAddress receiveAddress;

}
