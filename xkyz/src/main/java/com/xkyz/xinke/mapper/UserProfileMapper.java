package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserProfile;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "userMapperProfile")
public interface UserProfileMapper extends Mapper<UserProfile> {

}