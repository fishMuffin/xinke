package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.UserOrderMapper;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    UserOrderMapper userOrderMapper;

    public UserOrder getUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = UserOrder.builder().orderNo(orderNo).build();
        return userOrderMapper.selectOne(userOrder);
    }


    public int addUserOrder(UserOrder userOrder) {
        return userOrderMapper.insert(userOrder);
    }

    public int deleteUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = UserOrder.builder().orderNo(orderNo).build();
        return userOrderMapper.delete(userOrder);
    }

    public int updateUserOrder(String orderNo, Integer status) {
        UserOrder userOrder = this.getUserOrderByOrderNo(orderNo);
        if (userOrder == null) {
            throw new EmException(ExceptionEnums.USER_ORDER_NOT_EXIST);
        }
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo", userOrder.getOrderNo());
        userOrder.setStatus(status);
        return userOrderMapper.updateByExampleSelective(userOrder,example);
    }

    public List<UserOrder> getUserOrderListByOpenId(String openId) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openId", openId);
        return userOrderMapper.selectByExample(example);
    }

}
