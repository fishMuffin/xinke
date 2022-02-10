package com.xkyz.xinke.service;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.mapper.UserMapper;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    public static final String APPID = "wx79444d769f2eeabd";
    public static final String SECRET = "ed2d7b0b4ed7719a96f03d3abd2c7d85";
    @Autowired
    UserMapper userMapper;

    public User selectOne(String openId) {
        User user = User.builder().openId(openId).build();
        return userMapper.selectOne(user);
    }

    public User getOpenIdBySkey(String skey) {
        User user = User.builder().skey(skey).build();
        return userMapper.selectOne(user);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public ReturnUser login(String jsCode) {

        // 用户非敏感信息：rawData
        // 签名：signature
//        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(jsCode);
        // 3.接收微信接口服务 获取返回的参数
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
//        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
//        if (!signature.equals(signature2)) {
//            throw new EmException(ExceptionEnums.SIGN_CHECK_FAILURE);
//        }
//         5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        User userExam = User.builder().openId(openId).build();
        User user = userMapper.selectOne(userExam);
        int role = 1;
// uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            User build = User.builder().openId(openId).sessionKey(sessionKey).skey(skey).role(role).build();
            int insert = userMapper.insert(build);
        } else {
            skey = user.getSkey();
        }
//            user = new User();
//            user.setOpenId(openid);
//            user.setSkey(skey);
//            user.setCreateTime(new Date());
//            user.setLastVisitTime(new Date());
//            user.setSessionKey(sessionKey);
//            user.setCity(city);
//            user.setProvince(province);
//            user.setCountry(country);
//            user.setAvatarUrl(avatarUrl);
//            user.setGender(Integer.parseInt(gender));
//            user.setNickName(nickName);

//            this.userMapper.insert(user);
//        } else {
//            // 已存在，更新用户登录时间
////            user.setLastVisitTime(new Date());
////            // 重新设置会话skey
////            user.setSkey(skey);
////            this.userMapper.updateById(user);
//        }
//        //encrypteData比rowData多了appid和openid
//        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
//        //6. 把新的skey返回给小程序
//        GlobalResult result = GlobalResult.build(200, null, skey);
        ReturnUser returnUser = ReturnUser.builder().token(skey).role(role).build();
        return returnUser;
    }

}
