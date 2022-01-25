package com.xkyz.xinke;

import com.xkyz.xinke.controller.UserOrderController;
import com.xkyz.xinke.model.UserOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class UserOrderTest {
    @Autowired
    private UserOrderController userOrderController;

    @Test
    public void testGetUserOrderByUserId() {
        ResponseEntity<UserOrder> userOrderByOrderNo = userOrderController.getUserOrderByOrderNo("sdsfd");
        UserOrder body = userOrderByOrderNo.getBody();
        System.out.println(body);
    }

    @Test
    public void testGetUserOrderListByOpenId() {
        ResponseEntity<List<UserOrder>> list = userOrderController.getUserOrderListByOpenId("oVoNf5GAzZ3WULwRYyj4yppG4y6U");
        list.getBody().stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testAddUserOrder() {
        ResponseEntity<Integer> integerResponseEntity = userOrderController.addUserOrder(UserOrder.builder().orderNo("dfgfdg").orderTime("2022-1-12 08:00:00").receiveAddress(5).sendAddress(1).status(1).openId("oVoNf5GAzZ3WULwRYyj4yppG4y6U").price(543.55).stuffType("衣服").build());
        Integer body = integerResponseEntity.getBody();
        System.out.println(body);

    }

    @Test
    public void testDeleteUserOrderByOrderId() {
        ResponseEntity<Boolean> booleanResponseEntity = userOrderController.deleteUserOrderByOrderNo("sdsfd");
        Boolean body = booleanResponseEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void testUpdateUserOrderByOrderId() {
        ResponseEntity<Boolean> booleanResponseEntity = userOrderController.updateUserOrder("sdsfd", 2);
        Boolean body = booleanResponseEntity.getBody();
        System.out.println(body);
    }

}
