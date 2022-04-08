package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.ReturnMSG;
import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
@RestController()
@RequestMapping("/sys/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<ReturnUser> user_login(@RequestParam(value = "js_code") String jsCode
    ) {
        logger.info("UserController--user_login--js_code:" + jsCode);
        ReturnUser login = userService.login(jsCode);
        logger.info("UserController--user_login--return-login:" + login);
        return ResponseEntity.ok(login);
    }


    @GetMapping("updateUserProfile")
    @ResponseBody
    public ResponseEntity<ReturnMSG> updateUserProfile(@ApiParam("用户token") String userToken,
                                                       @ApiParam("用户头像") String portraitUrl,
                                                       @ApiParam("微信名称") String wxName
    ) {
        userService.updateUserOrder(userToken, portraitUrl, wxName);
        return ResponseEntity.ok(new ReturnMSG("ok"));
    }
//    @GetMapping("/getUserPhone")
//    @ResponseBody
//    public ResponseEntity<String> getUserPhone(@RequestParam(value = "js_code") String jsCode
//    ) {
//        String phoneNumber = userService.getPhoneNumber(jsCode);
//        return ResponseEntity.ok(phoneNumber);
//    }
//
//    @GetMapping("/parseUserInfo")
//    @ResponseBody
//    public ResponseEntity<String> parseUserInfo(@RequestParam(value = "js_code") String jsCode,
//                                                String encryptedData, String iv
//    ) {
//        String phoneNumber = userService.parseUserInfo(encryptedData,jsCode,iv);
//        return ResponseEntity.ok(phoneNumber);
//    }

}
