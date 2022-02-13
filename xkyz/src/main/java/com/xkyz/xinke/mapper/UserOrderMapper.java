package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;


@Component(value = "userOrderMapper")
public interface UserOrderMapper extends Mapper<UserOrder>{
    Double getStoreTodayIncome(
            @Param("pointsId") Integer pointsId,
            @Param("orderUpdateTime") Long orderUpdateTime);
    Double getStoreAllIncome(
            @Param("pointsId") Integer pointsId);
    Double getStoreTodayCount(
            @Param("pointsId") Integer pointsId,
            @Param("orderUpdateTime") Long orderUpdateTime);
    Double getCountByStatus(
            @Param("status") Integer status);

}