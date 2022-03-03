package com.xkyz.xinke;

import com.xkyz.xinke.controller.ExpressPriceReferenceController;
import com.xkyz.xinke.model.ExpressPriceReference;
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
        expressPriceReferenceController.addList();
    }


}
