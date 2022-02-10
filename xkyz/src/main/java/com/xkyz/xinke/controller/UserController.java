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

//    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
//    @PostMapping(value = "/add")
//    public String add(UserProfile user) {
//        return "success";
//    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<ReturnUser> user_login(@RequestParam(value = "js_code") String jsCode
    ) {
        ReturnUser login = userService.login(jsCode);
        return ResponseEntity.ok(login);
    }

//    @ApiOperation("更新用户")
//    @PostMapping(value = "/update")
//    public String update(
//            UserProfile user) {
//        return "success";
//    }

}
