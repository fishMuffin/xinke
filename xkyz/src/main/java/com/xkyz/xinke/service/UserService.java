package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserMapper;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectOne(String openId) {
        User user = User.builder().openId(openId).build();
        return userMapper.selectOne(user);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }
}
