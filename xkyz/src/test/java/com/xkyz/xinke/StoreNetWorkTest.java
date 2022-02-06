//package com.xkyz.xinke;
//
//import com.xkyz.xinke.controller.StorePointsController;
//import com.xkyz.xinke.controller.UserAddressController;
//import com.xkyz.xinke.model.StorePoints;
//import com.xkyz.xinke.pojo.PointEntity;
//import com.xkyz.xinke.util.MapUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//public class StoreNetWorkTest {
//    @Autowired
//    private StorePointsController storePointsController;
//
//    @Test
//    public void testGetAddress() {
//        PointEntity pointA = new PointEntity(32.876791, 115.790469);
//        List<StorePoints> body = storePointsController.getPointsByXAndY(pointA).getBody();
//        body.stream().forEach(System.out::println);
////        for (Map.Entry<Double, StorePoints> entry : body.entrySet()) {
////            System.out.println(entry.getKey() + "---" + entry.getValue());
////        }
//    }
//}
