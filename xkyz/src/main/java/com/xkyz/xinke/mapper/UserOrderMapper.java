package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.UserOrder;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@Component(value = "userOrderMapper")
public interface UserOrderMapper extends Mapper<UserOrder> {

}