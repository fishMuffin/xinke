package com.xkyz.xinke;

import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.controller.ExpressPriceReferenceController;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.ExpressPriceReferenceJitu;
import com.xkyz.xinke.pojo.ApiResult;
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
        System.out.println(expressPriceReferenceController.getPrice("湖北省", 6));

    }

    @Test
    public void testInsertList() {
//        ApiResult priceForJitu = expressPriceReferenceController.getPriceForJitu("湖北");
//        ApiResult priceForJitu1 = expressPriceReferenceController.getPriceForJitu("香港");
//        System.out.println(priceForJitu);
//        System.out.println(priceForJitu1);
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
