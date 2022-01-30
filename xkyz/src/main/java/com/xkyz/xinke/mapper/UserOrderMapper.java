package com.xkyz.xinke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xkyz.xinke.model.UserOrder;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


@Component(value = "userOrderMapper")
public interface UserOrderMapper extends Mapper<UserOrder>, BaseMapper<UserOrder> {

}