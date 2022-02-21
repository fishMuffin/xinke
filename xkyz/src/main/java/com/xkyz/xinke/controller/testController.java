package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/test")
public class testController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/hello")
    public String func() {
        return "hello world!";
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {// TODO 服务器不好使 本地上传没问题
//        logger.info("testController,time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " fileName:" + file.getOriginalFilename());
//        String s = uploadService.uploadImage(file);
//        return ResponseEntity.ok(s);
//    }

    //
}
