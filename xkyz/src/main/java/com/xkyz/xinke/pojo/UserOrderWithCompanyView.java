package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderWithCompanyView {


    public UserOrderWithCompanyView(UserAddress sendAddress, UserOrder userOrder, UserAddress receiveAddress, ExpressCompany expressCompany) {
        this.sendAddress = sendAddress;
        this.userOrder = userOrder;
        this.receiveAddress = receiveAddress;
        this.expressCompany = expressCompany;
    }

    public UserOrderWithCompanyView() {
    }


    private UserAddress sendAddress;
    private UserOrder userOrder;
    private UserAddress receiveAddress;
    private ExpressCompany expressCompany;

}
