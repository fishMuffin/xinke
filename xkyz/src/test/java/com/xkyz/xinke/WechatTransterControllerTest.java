package com.xkyz.xinke;

import com.xkyz.xinke.controller.WechatTransterController;
import com.xkyz.xinke.pojo.ReturnMSG;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WechatTransterControllerTest {
    @Autowired
    private WechatTransterController wechatTransterController;

    @Test
    public void test() {
        ResponseEntity<ReturnMSG> dsf = wechatTransterController.addWechatTransfer("824da98e-f39d-4932-b508-495e6c3b64ff", 123.33);
        System.out.println(dsf);

    }
}