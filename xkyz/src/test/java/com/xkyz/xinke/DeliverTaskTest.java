//package com.xkyz.xinke;
//
//import com.xkyz.xinke.controller.DeliverTaskController;
//import com.xkyz.xinke.controller.UserOrderController;
//import com.xkyz.xinke.model.DeliverTask;
//import com.xkyz.xinke.model.UserOrder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//@SpringBootTest
//public class DeliverTaskTest {
//    @Autowired
//    private DeliverTaskController deliverTaskController;
//
//
//    @Test
//    public void testGetList() {
//        ResponseEntity<List<DeliverTask>> list = deliverTaskController.getDeliverTaskList(1);
//        list.getBody().stream().forEach(s -> System.out.println(s));
//    }
//
//    @Test
//    public void testGet() {
//        ResponseEntity<DeliverTask> deliverTask = deliverTaskController.getDeliverTask(1);
//        System.out.println(deliverTask);
//    }
//
//    @Test
//    public void testUpdate() {
////        ResponseEntity<Boolean> booleanResponseEntity = userOrderController.deleteUserOrderByOrderNo("sdsfd");
////        Boolean body = booleanResponseEntity.getBody();
////        System.out.println(body);
//    }
//
//
//
//}
