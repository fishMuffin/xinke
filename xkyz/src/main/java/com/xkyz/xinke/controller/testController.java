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

    @PostMapping("upload")
    public int getResult(@RequestBody JSONObject req){
        System.out.println(req);

        int a=req.getInteger("num1");
        int b=req.getInteger("num2");

        System.out.println(a+" "+b);
        return a+b;
    }
}
