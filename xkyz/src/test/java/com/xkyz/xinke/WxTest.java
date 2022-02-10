package com.xkyz.xinke;

import com.xkyz.xinke.controller.ExpressCompanyController;
import com.xkyz.xinke.controller.SendWxMessage;
import com.xkyz.xinke.controller.StorePointsController;
import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.PointEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class WxTest {
    @Autowired
    private SendWxMessage sendWxMessage;

    @Test
    public void testGetAddress() {
        String accessToken = sendWxMessage.getAccessToken();
        System.out.println(accessToken);
    }
    @Test
    public void testPushOne(){
        String s = sendWxMessage.pushOneUser();
        System.out.println(s);
    }
}
