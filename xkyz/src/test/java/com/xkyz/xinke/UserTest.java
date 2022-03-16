package com.xkyz.xinke;

import com.xkyz.xinke.controller.UserController;
import com.xkyz.xinke.pojo.ReturnUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserController userController;

    @Test
    public void testLogin(){
        ResponseEntity<ReturnUser> user = userController.user_login("oVoNf5GAzZ3WULwRYyj4yppG4y6U");
        System.out.println(user);
    }
}
