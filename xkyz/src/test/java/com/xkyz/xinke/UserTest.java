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
        String code="023uM8ml2wauP84gThnl28GqXB1uM8mH";
        ResponseEntity<ReturnUser> user = userController.user_login(code);
        System.out.println(user);
    }


//    encryptedData: "a3UOgYfV0ftWU3VJA1OIHWrSgCx9tdHnbVWxyj+PsTS2hV2HlvZrYASuw7fQ5UHGmyskilAGLsREB802qsf7HajlRAzjvFjOIIzX5d+PDckpc6vPp+KjYtfD8ZqEyYSTDU2X18cygDqXMxvSEyji7tC0Qr7NuaQPqoLxcc70814fhTDZB58Aoz8NhkWtHp+aHlZF1LDJ1pXBnfFVQDPqNA=="
//
//    iv: "xDH6XGE1rmvxKOVGTbQUng=="
    @Test
    public void testParseUserPhone(){
        String encript="a3UOgYfV0ftWU3VJA1OIHWrSgCx9tdHnbVWxyj+PsTS2hV2HlvZrYASuw7fQ5UHGmyskilAGLsREB802qsf7HajlRAzjvFjOIIzX5d+PDckpc6vPp+KjYtfD8ZqEyYSTDU2X18cygDqXMxvSEyji7tC0Qr7NuaQPqoLxcc70814fhTDZB58Aoz8NhkWtHp+aHlZF1LDJ1pXBnfFVQDPqNA==";
        String iv="xDH6XGE1rmvxKOVGTbQUng==";
        String code="013qMlll28PJP84jlMol2ZdFd83qMllb";
        ResponseEntity<String> userPhone = userController.parseUserInfo(encript,code,iv);
        String body = userPhone.getBody();
        System.out.println(body);

    }

    @Test
    public void testGetUserPhone(){
        String code="3d2e9ab62cb97495315fa7aa8d39b1c2344013c2b3aad79b914bdf4c1f80c508";
        ResponseEntity<String> userPhone = userController.getUserPhone(code);
        String body = userPhone.getBody();
        System.out.println(body);

    }
}
