package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderMapper {
    public UserOrder getUserOrderByOrderNo(String orderNo);

//    public List<UserAddress> getUserAddressListByUserId(int userId);

    public int addUserOrder(UserOrder userOrder);

    public int deleteUserOrderByOrderNo(String orderNo);

    public int updateUserOrder(UserOrder userOrder);

}