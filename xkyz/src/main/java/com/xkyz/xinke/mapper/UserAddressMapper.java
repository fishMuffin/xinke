package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "userAddressMapper")
public interface UserAddressMapper extends Mapper<UserAddress> {
}

