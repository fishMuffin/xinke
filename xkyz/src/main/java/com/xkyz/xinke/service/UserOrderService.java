package com.xkyz.xinke.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.UserOrderMapper;
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
        //更新数据 订单更新时间 方便后期商家查询当日收益
        userOrder.setOrderUpdateTime(System.currentTimeMillis()/1000);
        return userOrderMapper.updateByExampleSelective(userOrder,example);
    }

    public List<UserOrder> getUserOrderListByOpenId(String token,Integer status) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userToken", token);
        criteria.andEqualTo("status", status);
        return userOrderMapper.selectByExample(example);
    }

    public Double getStoreTodayIncome(String token) {
//        select sum(price)
//                from xinke.user_order
//        where deliver_status = 2
//        and store_token = ''
//        and order_update_time > '';
        QueryWrapper<UserOrder> queryWrapper = new QueryWrapper<UserOrder>();
        queryWrapper.select("sum(price) as sumAll");
//        queryWrapper.eq("deliver_status",2);
        queryWrapper.eq("store_token",token);
//        queryWrapper.ge("order_update_time", TimeUtil.getTodayStartTime()/1000);
        List<UserOrder> list = userOrderMapper.selectList(queryWrapper);
//        Order ord= orderService.getOne(queryWrapper);
//        //注意，空指针问题
//        if (ord== null){
//            order.setSumAll(Double.valueOf(0)) ;
//        }else{
//            order.setSumAll(ord.getSumAll());
//        }

        return 0.0;
    }
}
