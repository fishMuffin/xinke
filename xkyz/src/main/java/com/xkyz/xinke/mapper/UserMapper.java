package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "userMapper")
public interface UserMapper extends Mapper<User> {

}