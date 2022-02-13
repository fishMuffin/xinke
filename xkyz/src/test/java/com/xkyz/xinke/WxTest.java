package com.xkyz.xinke;

import com.xkyz.xinke.controller.SendWxMessageController;
import com.xkyz.xinke.controller.WxPayController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest
public class WxTest {
    @Autowired
    private SendWxMessageController sendWxMessage;
    @Autowired
    private WxPayController wxPayController;

    @Test
    public void testUnifiedOrder(){
        ResponseEntity<Map<String, String>> mapResponseEntity = wxPayController.unifiedOrder();
        Map<String, String> body = mapResponseEntity.getBody();
        System.out.println(body);
    }



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
    @Test
    public void testNotify(){
        String s1 = sendWxMessage.wxNotify("824da98e-f39d-4932-b508-495e6c3b64ff");
        System.out.println(s1);
    }
}
