package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.ExpressPriceReferenceJitu;
import com.xkyz.xinke.pojo.ApiResult;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.pojo.ExpressPriceReferenceJituView;
import com.xkyz.xinke.pojo.ExpressPriceReferenceView;
import com.xkyz.xinke.service.DeliverTaskService;
import com.xkyz.xinke.service.ExpressCompanyService;
import com.xkyz.xinke.service.ExpressPriceReferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Api(tags = "快递公司价格参照表")
@RestController()
@RequestMapping("/sys/priceReference")
public class ExpressPriceReferenceController {
    private static final Logger logger = LoggerFactory.getLogger(ExpressPriceReferenceController.class);

    @Autowired
    private ExpressPriceReferenceService expressPriceReferenceService;

    @ApiOperation("根据目的地返回JD快递的价格")
    @GetMapping(value = "/get")
    public ResponseEntity<List<ExpressPriceReferenceView>> getPrice(@ApiParam("目的地(务必提供正确完整的省份名称比如：湖北省)") String destination, @ApiParam("快递公司id") Integer companyId) {
        logger.info("ExpressPriceReferenceController getPriceJD destination:" + destination);
        List<ExpressPriceReferenceView> price = expressPriceReferenceService.getPrice(destination, companyId);
        if(price==null){
            new ResponseEntity<>("该省份(市)业务暂时不支持"+destination, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(price);
    }

    @ApiOperation("根据目的地返回极兔的价格")
    @GetMapping(value = "/getPriceForJitu")
    public ApiResult getPriceForJitu(@ApiParam("目的地(务必提供正确完整的省份名称比如：湖北省)") String destination) {
        logger.info("ExpressPriceReferenceController getPriceForJitu destination:" + destination);
        ExpressPriceReferenceJituView priceForJitu = expressPriceReferenceService.getPriceForJitu(destination);
        if(priceForJitu==null){
            return ApiResult.builder().code(HttpStatus.BAD_REQUEST+"").message("该地区不在配送范围:"+destination).success(false).build();
        }
        ResponseEntity<ExpressPriceReferenceJituView> res = ResponseEntity.ok(priceForJitu);
        return ApiResult.builder().code(200+"").data(res).success(true).build();
    }


}
