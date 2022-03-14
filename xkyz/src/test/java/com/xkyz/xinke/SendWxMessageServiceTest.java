package com.xkyz.xinke;

import com.xkyz.xinke.service.SendWxMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SendWxMessageServiceTest {
    @Autowired
    SendWxMessageService sendWxMessageService;

    @Test
    public void test1(){
//        sendWxMessageService.pushMesToDeliverTest();
    }
}