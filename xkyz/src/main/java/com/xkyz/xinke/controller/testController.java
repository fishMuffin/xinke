package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/test")
public class testController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/hello")
    public String func() {
        return "hello world!";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {
        String s = uploadService.uploadImage(file);
        return ResponseEntity.ok(s);
    }
}
