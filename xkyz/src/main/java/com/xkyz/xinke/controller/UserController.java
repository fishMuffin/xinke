package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.service.UserService;
import com.xkyz.xinke.util.WechatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
@RestController(value = "/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/add")
    public String add(User user) {
        return "success";
    }

//    @ApiOperation("根据ID查询用户")
//    @PostMapping(value = "/user")
//    //作用在参数上，对参数进行说明
//    public User login(
//            @RequestParam(value = "code", required = false) String code,
//            @RequestParam(value = "rawData", required = false) String rawData,
//            @RequestParam(value = "signature", required = false) String signature,
//            @RequestParam(value = "encrypteData", required = false) String encrypteData,
//            @RequestParam(value = "iv", required = false) String iv
//    ) {
//        User user = userService.selectUserById(1);
//        return user;
//    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> user_login(@RequestParam(value = "code", required = false) String code,
                                             @RequestParam(value = "rawData", required = false) String rawData,
                                             @RequestParam(value = "signature", required = false) String signature,
                                             @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                             @RequestParam(value = "iv", required = false) String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            throw new EmException(ExceptionEnums.SIGN_CHECK_FAILURE);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
//        User user =userService.selectUserById(openid);//TODO 修改数据库 改成String类型
        User user = userService.selectUserById(1);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

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
        } else {
            // 已存在，更新用户登录时间
//            user.setLastVisitTime(new Date());
//            // 重新设置会话skey
//            user.setSkey(skey);
//            this.userMapper.updateById(user);
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
//        GlobalResult result = GlobalResult.build(200, null, skey);
        return ResponseEntity.ok(skey);
    }

    @ApiOperation("更新用户")
    @PostMapping(value = "/update")
    public String update(
            @ApiParam("用户对象") User user) {
        return "success";
    }

    @ApiOperation("删除用户")
    @GetMapping(value = "/delete")
    public Integer delete(@ApiParam("用户ID") Integer id) {
        return 1;
    }
}
