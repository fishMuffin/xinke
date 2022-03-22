package com.xkyz.xinke.service;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.controller.ImageUploadController;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.UserMapper;
import com.xkyz.xinke.mapper.UserProfileMapper;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.model.UserProfile;
import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.util.WechatUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    public static final String APPID = "wx79444d769f2eeabd";
    public static final String SECRET = "ed2d7b0b4ed7719a96f03d3abd2c7d85";
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserProfileMapper userProfileMapper;

    public User selectOne(String openId) {
        User user = User.builder().openId(openId).build();
        return userMapper.selectOne(user);
    }

    public List<String> getListByPointId(Integer pointId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pointId", pointId);
        List<User> users = userMapper.selectByExample(example);
        List<String> list = users.stream().map(User::getOpenId).collect(Collectors.toList());
        return list;
    }

    public List<User> getListByRole(Integer role) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("role", role);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    public Integer getPointsOwnerByUserToken(String userToken) {
        User user = User.builder().skey(userToken).role(1).build();
        User res = userMapper.selectOne(user);
        return res.getPointId();
    }

    public String getOpenIdBySkey(String userToken) {
        logger.info("UserService--getOpenIdBySkey:" + userToken);
        if (StringUtils.isEmpty(userToken)) throw new EmException(ExceptionEnums.USER_TOKEN_CANNOT_BE_EMPTY);
        User user = User.builder().skey(userToken).build();
        User res = userMapper.selectOne(user);
        if (res == null) throw new EmException(ExceptionEnums.INVALID_USER_TOKEN);
        return res.getOpenId();
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public String getPhoneNumber(String jsCode) {
        logger.info("UserService--getPhoneNumber--jsCode:" + jsCode);
        JSONObject user = WechatUtil.getUserInfoWithinPhone(jsCode);
        String phone_info = user.getString("phone_info");
        logger.info("UserService--getPhoneNumber--phone_info:" + phone_info);
        return phone_info;
    }

    public String parseUserInfo(String encryptedData, String code, String iv) {
        logger.info("UserService--parseUserInfo--code:" + code);
        String phone_info = WechatUtil.parseUserInfo(encryptedData, code, iv);
        logger.info("UserService--parseUserInfo--code:" + code);
        return phone_info;
    }


    public int updateUserOrder(String jsCode, String portraitUrl, String wxName) {

        Example example = new Example(UserProfile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skey", jsCode);
        UserProfile userProfile = UserProfile.builder().portrait_url(portraitUrl).wx_name(wxName).build();
        return userProfileMapper.updateByExampleSelective(userProfile, example);
    }

    public ReturnUser login(String jsCode) {
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(jsCode);
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        if (openId == null || sessionKey == null) throw new EmException(ExceptionEnums.INVALID_USER_CODE);
        User userExam = User.builder().openId(openId).build();
        User user = userMapper.selectOne(userExam);

// uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            User build = User.builder().openId(openId).sessionKey(sessionKey).skey(skey).role(2).build();
            UserProfile userProfile = UserProfile.builder().skey(skey).build();
            logger.info("UserService--login--userToInsert:" + build.toString());
            userMapper.insert(build);
            userProfileMapper.insert(userProfile);
            return ReturnUser.builder().token(skey).role(2).build();
        } else {
            int role = user.getRole();
            skey = user.getSkey();
            UserProfile userProfile = userProfileMapper.selectOne(UserProfile.builder().skey(skey).build());
            return ReturnUser.builder().token(skey).role(role).wxName(userProfile.getWx_name()).portraitUrl(userProfile.getPortrait_url()).build();
        }
    }

}
