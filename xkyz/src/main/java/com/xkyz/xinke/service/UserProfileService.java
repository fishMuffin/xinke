package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
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
        UserProfile profile = userProfileMapper.selectOne(userProfile);
        if (profile==null) throw new EmException(ExceptionEnums.INVALID_USER_TOKEN);
        return profile;
    }

    public String getPhoneNumberByUserToken(String userToken){
        UserProfile userProfile = this.selectOne(userToken);
        return userProfile.getPhoneNumber();
    }

    public String getNameByUserToken(String userToken){
        UserProfile userProfile = this.selectOne(userToken);
        return userProfile.getWx_name();
    }
}
