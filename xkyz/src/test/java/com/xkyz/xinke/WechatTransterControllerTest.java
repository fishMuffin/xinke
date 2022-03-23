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
        ResponseEntity<ReturnMSG> dsf = wechatTransterController.addWechatTransfer("6532a4b9-2628-4aa2-9f52-4eec87382289", 2.0);
        System.out.println(dsf);

    }
}