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


//    @Test
//    public void testGetUserOrderByUserId() {
//        ResponseEntity<UserOrderView> list = userOrderController.getUserOrderByOrderNo("dsfdsfgdsg1");
//        UserOrderView body = list.getBody();
//        System.out.println(body);
//    }

    @Test
    public void testGetUserOrderListByOpenId() {
        ResponseEntity<List<UserOrderView>> list = userOrderController.getUserOrderListByOpenId("824da98e-f39d-4932-b508-495e6c3b64ff",1);
        list.getBody().stream().forEach(s -> System.out.println(s));
    }
    @Test
    public void testGetUserOrderListByDeliverToken() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getUserOrderListByDeliverToken("2", 2);
        list.getBody().stream().forEach(s -> System.out.println(s));
    }
    @Test
    public void testGetUserOrderListByDeliverOpenId() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getListByPointsId(1);
        list.getBody().stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testNewList() {
        ResponseEntity<List<UserOrderWithCompanyView>> list = userOrderController.getNewList("2",1,1);
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
        ResponseEntity<ReturnMSG> ggg = userOrderController.updateUserOrder(UserOrder.builder().orderNo("ggg").price(1000.00).stuffType("4").estimatedWeight(100.00).imageUrl("www.baidu.com").expressCompanyId(3).build());
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
        ResponseEntity<IncomeView> entity = userOrderController.storeTodayIncome("824da98e-f39d-4932-b508-495e6c3b64ff");
        IncomeView body = entity.getBody();
        System.out.println(body);
    }
}
