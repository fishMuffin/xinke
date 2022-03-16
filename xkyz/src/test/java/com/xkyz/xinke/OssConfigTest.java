package com.xkyz.xinke;

import com.xkyz.xinke.pojo.UploadVideoProperties;
import com.xkyz.xinke.service.UploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OssConfigTest {

    @Autowired
    private UploadService uploadService;


    @Test
    public void test(){
        uploadService.testVideoProperties();
    }



}
