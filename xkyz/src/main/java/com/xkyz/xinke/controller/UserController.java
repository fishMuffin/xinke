package com.xkyz.xinke.controller;

import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
@RestController()
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<ReturnUser> user_login(@RequestParam(value = "js_code") String jsCode
    ) {
        ReturnUser login = userService.login(jsCode);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/getUserPhone")
    @ResponseBody
    public ResponseEntity<String> getUserPhone(@RequestParam(value = "js_code") String jsCode
    ) {
        String phoneNumber = userService.getPhoneNumber(jsCode);
        return ResponseEntity.ok(phoneNumber);
    }

    @GetMapping("/parseUserInfo")
    @ResponseBody
    public ResponseEntity<String> parseUserInfo(@RequestParam(value = "js_code") String jsCode,
                                                String encryptedData, String iv
    ) {
        String phoneNumber = userService.parseUserInfo(encryptedData,jsCode,iv);
        return ResponseEntity.ok(phoneNumber);
    }

}
