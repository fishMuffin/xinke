package com.xkyz.xinke;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.service.SendWxMessageService;
import com.xkyz.xinke.controller.WxPayController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
public class WxTest {
    @Autowired
    private SendWxMessageService sendWxMessage;
    @Autowired
    private WxPayController wxPayController;

    @Test
    public void testUnifiedOrder(){
//        |String userToken="824da98e-f39d-4932-b508-495e6c3b64ff";
//            String orderNo="dsfdsfgdsgfdgfd";
//            Integer amount=100;
        ResponseEntity<Map<String, String>> mapResponseEntity = wxPayController.
                unifiedOrder(100.0,"824da98e-f39d-4932-b508-495e6c3b64ff","fghfh");
        Map<String, String> body = mapResponseEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void testTransfer(){
        ResponseEntity<String> transfer = wxPayController.transfer("824da98e-f39d-4932-b508-495e6c3b64ff");
        String body = transfer.getBody();
        System.out.println(body);
    }

    @Test
    public void testExeception(){
        throw new EmException(ExceptionEnums.INVALID_USER_TOKEN);
//        System.out.println(ExceptionEnums.INVALID_USER_TOKEN.getCode()+""+ExceptionEnums.INVALID_USER_TOKEN.getMsg());

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
