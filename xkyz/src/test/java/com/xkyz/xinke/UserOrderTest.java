package com.xkyz.xinke;

import com.xkyz.xinke.controller.UserOrderController;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.IncomeView;
import com.xkyz.xinke.pojo.ReturnMSG;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.pojo.UserOrderWithCompanyView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class UserOrderTest {
    @Autowired
    private UserOrderController userOrderController;

    //    @Test
//    public void testGetStoreTodayIncome() {
//        ResponseEntity<IncomeView> incomeViewResponseEntity = userOrderController.storeTodayIncome(1);
//        IncomeView body = incomeViewResponseEntity.getBody();
//        System.out.println(body);
//    }
//    @Test
//    public void testCancelOrder() {
//        ResponseEntity<ReturnMSG> returnMSGResponseEntity = userOrderController.cancelUserOrder("dsfdsfgdsgfdgfd");
//        ReturnMSG body = returnMSGResponseEntity.getBody();
//        System.out.println(body);
//    }


    @Test
    public void testGetUserOrderByUserId() {
        ResponseEntity<UserOrderView> list = userOrderController.getUserOrderByOrderNo("0ff28d1c-a76d-4b0e-96e9-eb6e9dd8");
        UserOrderView body = list.getBody();
        System.out.println(body);
    }

    @Test
    public void testGetUserOrderListByOpenId() {
        ResponseEntity<List<UserOrderView>> list = userOrderController.getUserOrderListByOpenId("3cc7b689-337c-4966-a25a-a8061a6e0399",2);
//        Collections.sort(list.getBody(),new StudentComparator());
        list.getBody().stream().forEach(s -> System.out.println(s.getUserOrder().getOrderUpdateTime()));
    }
    @Test
    public void testGetUserOrderListByDeliverToken() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getUserOrderListByDeliverToken("2", 2);
        ResponseEntity<List<UserOrderWithCompanyView>> list1 = userOrderController.getUserOrderListByDeliverToken("", 2);
        list.getBody().stream().forEach(s -> System.out.println(s));
        list1.getBody().stream().forEach(s -> System.out.println(s));
    }
    @Test
    public void testGetUserOrderListByDeliverOpenId() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getListByPointsId(1);
        list.getBody().stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testNewList() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getNewList(2,5);
        list.getBody().stream().forEach(s -> System.out.println(s));
    }
//
    @Test
    public void testAddUserOrder() {
        ResponseEntity<ReturnMSG> returnMSGResponseEntity = userOrderController.addUserOrder(UserOrder.builder().orderNo("dfgfdg").orderTime(1646213325L).pointsId(1).receiveAddress(8).sendAddress(9).status(1).deliverToken("824da98e-f39d-4932-b508-495e6c3b64ff").userToken("824da98e-f39d-4932-b508-495e6c3b64ff").price(543.55).stuffType("衣服").build());
        ReturnMSG body = returnMSGResponseEntity.getBody();
        System.out.println(body);

    }
////
////    @Test
////    public void testDeleteUserOrderByOrderId() {
////        ResponseEntity<Boolean> booleanResponseEntity = userOrderController.deleteUserOrderByOrderNo("sdsfd");
////        Boolean body = booleanResponseEntity.getBody();
////        System.out.println(body);
////    }
//
    @Test
    public void testUpdateUserOrderByOrderId() {
        ResponseEntity<ReturnMSG> ggg = userOrderController.updateUserOrder(UserOrder.builder().orderNo("1070bcf7-0afd-415d-84f8-1fe6ce12").userToken("3cc7b689-337c-4966-a25a-a8061a6e0399").deliverToken("0b86aea5-881e-46cc-91c5-0aed18a44676").price(1000.00).stuffType("4").estimatedWeight(100.00).imageUrl("www.baidu.com").pointsId(5).expressCompanyId(9).build());
        ReturnMSG body = ggg.getBody();
        System.out.println(body);
    }

    @Test
    public void testFinishOrder() {
        ResponseEntity<ReturnMSG> ggg = userOrderController.finishOrder("fghfh");
        ReturnMSG body = ggg.getBody();
        System.out.println(body);
    }
    @Test
    public void testGetStoreTodayIncome() {
        ResponseEntity<IncomeView> entity = userOrderController.storeTodayIncome("db5a9a77-febc-405c-acf5-d036d9737878");
        IncomeView body = entity.getBody();
        System.out.println(body);
    }
}
