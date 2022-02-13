//package com.xkyz.xinke;
//
//import com.xkyz.xinke.controller.UserAddressController;
//import com.xkyz.xinke.model.UserAddress;
//import com.xkyz.xinke.pojo.ReturnMSG;
//import com.xkyz.xinke.pojo.UserAddressWithUserProfileView;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//@SpringBootTest
//public class UserAddressTest {
//    @Autowired
//    private UserAddressController userAddressController;
//
//    @Test
//    public void testGetUserAddressListByUserId() {
//        ResponseEntity<List<UserAddressWithUserProfileView>> list = userAddressController.getUserAddressListByOpenId("824da98e-f39d-4932-b508-495e6c3b64ff");
//        list.getBody().stream().forEach(s -> System.out.println(s));
//    }
//
//    @Test
//    public void testAddUserAddress() {
////        ResponseEntity<Integer> integerResponseEntity = userAddressController.addUserAddress(UserAddress.builder().userName("张三").phoneNumber("15395830950").city("杭州市").province("浙江省").addressDetail("小河路").district("拱墅区").skey("824da98e-f39d-4932-b508-495e6c3b64ff").build());
//        ResponseEntity<ReturnMSG> returnMSGResponseEntity = userAddressController.addUserAddress(UserAddress.builder().userName("李大宝").phoneNumber("13333333333").city("阜阳市").province("安徽省").addressDetail("一道河中路129号").district("颍州区").skey("824da98e-f39d-4932-b508-495e6c3b64ff").build());
////        {"skey":"824da98e-f39d-4932-b508-495e6c3b64ff","userName":"李大宝","phoneNumber":"13333333333","province":"安徽省","city":"阜阳市","district":"颍州区","addressDetail":"一道河中路129号"}
//        System.out.println(returnMSGResponseEntity.getBody());
//
//    }
//
//    @Test
//    public void testDeleteUserAddressByAddressId() {
//        ResponseEntity<ReturnMSG> returnMSGResponseEntity = userAddressController.deleteUserAddressByAddressId(11);
//        ReturnMSG body = returnMSGResponseEntity.getBody();
//        System.out.println(body);
//    }
////
////    @Test
////    public void testUpdateUserAddressByAddressId() {
////        ResponseEntity<Boolean> booleanResponseEntity = userAddressController.updateUserAddressByAddressId(UserAddress.builder().userId(1).addressId(5).city("武汉市").province("湖北省").addressDetail("bbb").district("ccc").build());
////        Boolean body = booleanResponseEntity.getBody();
////        System.out.println(body);
////    }
//
//}
