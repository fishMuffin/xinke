package com.xkyz.xinke;

import com.xkyz.xinke.controller.UserController;
import com.xkyz.xinke.pojo.ReturnMSG;
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
        String code="0737tl000qGDwN1hNd200GxE3007tl0h";
        ResponseEntity<ReturnUser> user = userController.user_login(code);
        System.out.println(user);
    }
    @Test
    public void testUpdateUserProfile(){
        String code="6532a4b9-2628-4aa2-9f52-4eec87382289";
        ResponseEntity<ReturnMSG> updateUserProfile = userController.updateUserProfile(code, "www.baidu.com", "新客");
        System.out.println(updateUserProfile);
    }


//    encryptedData: "a3UOgYfV0ftWU3VJA1OIHWrSgCx9tdHnbVWxyj+PsTS2hV2HlvZrYASuw7fQ5UHGmyskilAGLsREB802qsf7HajlRAzjvFjOIIzX5d+PDckpc6vPp+KjYtfD8ZqEyYSTDU2X18cygDqXMxvSEyji7tC0Qr7NuaQPqoLxcc70814fhTDZB58Aoz8NhkWtHp+aHlZF1LDJ1pXBnfFVQDPqNA=="
//
//    iv: "xDH6XGE1rmvxKOVGTbQUng=="
//    @Test
//    public void testParseUserPhone(){
//        String encript="a3UOgYfV0ftWU3VJA1OIHWrSgCx9tdHnbVWxyj+PsTS2hV2HlvZrYASuw7fQ5UHGmyskilAGLsREB802qsf7HajlRAzjvFjOIIzX5d+PDckpc6vPp+KjYtfD8ZqEyYSTDU2X18cygDqXMxvSEyji7tC0Qr7NuaQPqoLxcc70814fhTDZB58Aoz8NhkWtHp+aHlZF1LDJ1pXBnfFVQDPqNA==";
//        String iv="xDH6XGE1rmvxKOVGTbQUng==";
//        String code="013qMlll28PJP84jlMol2ZdFd83qMllb";
//        ResponseEntity<String> userPhone = userController.parseUserInfo(encript,code,iv);
//        String body = userPhone.getBody();
//        System.out.println(body);
//
//    }

//    @Test
//    public void testGetUserPhone(){
//        String code="3d2e9ab62cb97495315fa7aa8d39b1c2344013c2b3aad79b914bdf4c1f80c508";
//        ResponseEntity<String> userPhone = userController.getUserPhone(code);
//        String body = userPhone.getBody();
//        System.out.println(body);
//
//    }
}
