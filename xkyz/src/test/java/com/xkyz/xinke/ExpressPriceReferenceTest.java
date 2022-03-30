package com.xkyz.xinke;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.controller.ExpressPriceReferenceController;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.ExpressPriceReferenceJitu;
import com.xkyz.xinke.pojo.ExpressPriceReferenceJituView;
import com.xkyz.xinke.pojo.ExpressPriceReferenceView;
import com.xkyz.xinke.service.ExpressPriceReferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ExpressPriceReferenceTest {
    @Autowired
    private ExpressPriceReferenceController expressPriceReferenceController;

    @Test
    public void testGetList() {
        ResponseEntity<List<ExpressPriceReferenceView>> price = expressPriceReferenceController.getPrice("湖北省", 1);
        List<ExpressPriceReferenceView> list = price.getBody();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testInsertList() {
        ResponseEntity<ExpressPriceReferenceJituView> priceForJitu = expressPriceReferenceController.getPriceForJitu("新疆");
        ExpressPriceReferenceJituView body = priceForJitu.getBody();
        String s = JSONObject.toJSONString(body);
        System.out.println(s);
    }

    @Test
    public void testdataConvert() {
        String s1 = dataConvert("广西壮族自治区");
        String s2 = dataConvert("广西壮族直辖市");
        System.out.println(s1);
        System.out.println(s2);
    }

    private String dataConvert(String destination) {
        if (destination.contains("直辖市")) {
            int i = destination.indexOf("直辖市");
            return destination.substring(0, i);
        } else if (destination.contains("市")) {
            int i = destination.indexOf("市");
            return destination.substring(0, i);
        } else if (destination.contains("省")) {
            int i = destination.indexOf("省");
            return destination.substring(0, i);
        } else if (destination.contains("自治区")) {
            int i = destination.indexOf("自治区");
            return destination.substring(0, i);
        } else {
            return destination;
        }
    }


}
