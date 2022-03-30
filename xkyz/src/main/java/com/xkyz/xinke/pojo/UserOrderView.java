package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderView implements Comparable {


    public UserOrderView(ExpressCompany expressCompany, UserAddress sendAddress, UserOrder userOrder, UserAddress receiveAddress) {
        this.expressCompany = expressCompany;
        this.sendAddress = sendAddress;
        this.userOrder = userOrder;
        this.receiveAddress = receiveAddress;
    }

    public UserOrderView() {
    }


    private ExpressCompany expressCompany;
    private UserAddress sendAddress;
    private UserOrder userOrder;
    private UserAddress receiveAddress;

    @Override
    public int compareTo(Object o) {
        return this.getUserOrder().getOrderUpdateTime().compareTo(this.getUserOrder().getOrderUpdateTime());
    }
}
