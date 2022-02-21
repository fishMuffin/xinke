package com.xkyz.xinke;

import com.xkyz.xinke.controller.DeliverTaskController;
import com.xkyz.xinke.controller.UserOrderController;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.DeliverTaskView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class DeliverTaskTest {
    @Autowired
    private DeliverTaskController deliverTaskController;


    @Test
    public void testGetList() {
        ResponseEntity<List<DeliverTaskView>> list = deliverTaskController.getDeliverTaskList("824da98e-f39d-4932-b508-495e6c3b64ff");
        list.getBody().stream().forEach(s -> System.out.println(s));
    }
//    @Test
//    public void testGet() {
//        ResponseEntity<DeliverTask> deliverTask = deliverTaskController.getDeliverTask(1);
//        System.out.println(deliverTask);
//    }
//
//    @Test
//    public void testUpdate() {
//        ResponseEntity<Boolean> booleanResponseEntity = deliverTaskController.updateUserOrder(1,2);
//        Boolean body = booleanResponseEntity.getBody();
//        System.out.println(body);
//    }
//


}
