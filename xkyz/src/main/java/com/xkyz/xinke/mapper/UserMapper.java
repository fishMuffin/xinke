package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User selectUserById(int id);

    public User selectUserByName(String name);

    public int insertUser(User user);

}