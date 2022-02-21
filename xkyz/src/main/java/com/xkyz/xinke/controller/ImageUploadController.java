package com.xkyz.xinke.controller;

import com.xkyz.xinke.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "图片上传")
@RestController()
@RequestMapping("/sys/image")
public class ImageUploadController {
    @Autowired
    private UploadService uploadService;
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("imageUpload,time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " fileName:" + file.getOriginalFilename());
        String s = uploadService.uploadImageOss(file);
        return ResponseEntity.ok(s);
    }
}
