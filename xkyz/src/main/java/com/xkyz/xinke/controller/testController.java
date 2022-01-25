package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class testController {
    @GetMapping("/hello")
    public String func(){
        return "hello world!";
    }
}
