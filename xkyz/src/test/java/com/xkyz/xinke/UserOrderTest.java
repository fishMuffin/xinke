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
    public void testGetUserOrderListByUserId() {
        ResponseEntity<UserOrder> userOrderByOrderNo = userOrderController.getUserOrderByOrderNo("32432r3245");
        UserOrder body = userOrderByOrderNo.getBody();
        System.out.println(body);
    }

    @Test
    public void testAddUserOrder() {
        ResponseEntity<Integer> integerResponseEntity = userOrderController.addUserOrder(UserOrder.builder().orderNo("sdsfd").orderTime("2022-1-12 08:00:00").receiveAddress(5).sendAddress(1).status(1).stuffType("衣服").build());
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
        ResponseEntity<Boolean> booleanResponseEntity = userOrderController.updateUserOrder(UserOrder.builder().orderNo("sdsfd").orderTime("2022-1-1 08:00:00").receiveAddress(5).sendAddress(1).status(1).stuffType("衣服").build());
        Boolean body = booleanResponseEntity.getBody();
        System.out.println(body);
    }

}
