package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.UserProfileMapper;
import com.xkyz.xinke.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    UserProfileMapper userProfileMapper;

    public UserProfile selectOne(String skey) {
        UserProfile userProfile = UserProfile.builder().skey(skey).build();
        return userProfileMapper.selectOne(userProfile);
    }


}
