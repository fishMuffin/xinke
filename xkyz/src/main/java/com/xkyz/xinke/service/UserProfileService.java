package com.xkyz.xinke.service;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.mapper.UserMapper;
import com.xkyz.xinke.mapper.UserProfileMapper;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserProfile;
import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileService {
    @Autowired
    UserProfileMapper userProfileMapper;

    public UserProfile selectOne(String skey) {
        UserProfile userProfile = UserProfile.builder().skey(skey).build();
        return userProfileMapper.selectOne(userProfile);
    }


}
