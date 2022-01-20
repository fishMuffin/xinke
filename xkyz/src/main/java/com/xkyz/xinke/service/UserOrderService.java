package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserOrderMapper;
import com.xkyz.xinke.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    UserOrderMapper userOrderMapper;

    public UserOrder getUserOrderByOrderNo(String orderId) {
        return userOrderMapper.getUserOrderByOrderNo(orderId);
    }


    public int addUserOrder(UserOrder userOrder) {
        return userOrderMapper.addUserOrder(userOrder);
    }

    public int deleteUserOrderByOrderNo(String orderNo) {
        return userOrderMapper.deleteUserOrderByOrderNo(orderNo);
    }

    public int updateUserOrder(UserOrder userOrder) {
        return userOrderMapper.updateUserOrder(userOrder);
    }


}
