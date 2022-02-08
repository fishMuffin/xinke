package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.service.DeliverTaskService;
import com.xkyz.xinke.service.ExpressCompanyService;
import com.xkyz.xinke.service.ExpressPriceReferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "快递公司价格参照表")
@RestController()
@RequestMapping("/sys/priceReference")
public class ExpressPriceReferenceController {
    @Autowired
    private ExpressPriceReferenceService expressPriceReferenceService;

    @ApiOperation("根据目的地计算价格")
    @GetMapping(value = "/get")
    public ResponseEntity<ExpressPriceReference> getPrice(@ApiParam("目的地(务必提供正确完整的省份名称比如：湖北省)") String destination,@ApiParam("快递公司Id") Integer companyId) {
        ExpressPriceReference price = expressPriceReferenceService.getPrice(destination, companyId);
        return ResponseEntity.ok(price);
    }


}
