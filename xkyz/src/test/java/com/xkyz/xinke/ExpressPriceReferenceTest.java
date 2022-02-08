package com.xkyz.xinke;

import com.xkyz.xinke.controller.ExpressPriceReferenceController;
import com.xkyz.xinke.model.ExpressPriceReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ExpressPriceReferenceTest {
    @Autowired
    private ExpressPriceReferenceController expressPriceReferenceController;


    @Test
    public void testGetList() {
        ResponseEntity<ExpressPriceReference> price = expressPriceReferenceController.getPrice("湖北省", 1);
        ExpressPriceReference body = price.getBody();
        System.out.println(body);
    }



}
