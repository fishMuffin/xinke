package com.xkyz.xinke;

import com.xkyz.xinke.controller.SendWxMessageController;
import com.xkyz.xinke.controller.WxPayController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
public class WxTest {
    @Autowired
    private SendWxMessageController sendWxMessage;
    @Autowired
    private WxPayController wxPayController;

    @Test
    public void testUnifiedOrder(){
//        |String userToken="824da98e-f39d-4932-b508-495e6c3b64ff";
//            String orderNo="dsfdsfgdsgfdgfd";
//            Integer amount=100;
        ResponseEntity<Map<String, String>> mapResponseEntity = wxPayController.
                unifiedOrder(100.0,"824da98e-f39d-4932-b508-495e6c3b64ff","34435345436666");
        Map<String, String> body = mapResponseEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void testMap(){
        Map<String,String> map=new HashMap<>();
        map.put("nor","1");
        map.put("appid","3");
        map.put("body","7");
        map.put("hh","2");
        Map<String,String> map1=new LinkedHashMap<>();
        map1.put("nor","1");
        map1.put("appid","3");
        map1.put("body","7");
        map1.put("hh","2");
        System.out.println(map);
        System.out.println(map1);
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
