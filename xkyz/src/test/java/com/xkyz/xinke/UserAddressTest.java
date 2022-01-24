//package com.xkyz.xinke;
//
//import com.xkyz.xinke.controller.UserAddressController;
//import com.xkyz.xinke.model.UserAddress;
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
//        ResponseEntity<List<UserAddress>> list = userAddressController.getUserAddressListByUserId(1);
//        list.getBody().stream().forEach(s -> System.out.println(s));
//    }
//
//    @Test
//    public void testAddUserAddress() {
//        ResponseEntity<Integer> integerResponseEntity = userAddressController.addUserAddress(UserAddress.builder().userId(1).city("杭州市").province("浙江省").addressDetail("小河路").district("拱墅区").build());
//        Integer body = integerResponseEntity.getBody();
//        System.out.println(body);
//
//    }
//
//    @Test
//    public void testDeleteUserAddressByAddressId() {
//        ResponseEntity<Boolean> booleanResponseEntity = userAddressController.deleteUserAddressByAddressId(6);
//        Boolean body = booleanResponseEntity.getBody();
//        System.out.println(body);
//    }
//
//    @Test
//    public void testUpdateUserAddressByAddressId() {
//        ResponseEntity<Boolean> booleanResponseEntity = userAddressController.updateUserAddressByAddressId(UserAddress.builder().userId(1).addressId(5).city("武汉市").province("湖北省").addressDetail("bbb").district("ccc").build());
//        Boolean body = booleanResponseEntity.getBody();
//        System.out.println(body);
//    }
//
//}
