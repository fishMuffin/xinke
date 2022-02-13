package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.PointEntity;
import com.xkyz.xinke.service.ExpressCompanyService;
import com.xkyz.xinke.service.StorePointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "快递公司API")//作用在模块API类上，对API模块进行说明
@RestController()
@RequestMapping("/sys/expressCompany")
public class ExpressCompanyController {
    @Autowired
    private ExpressCompanyService expressCompanyService;

    @ApiOperation("获取快递公司列表")
    @PostMapping(value = "/list")
    //作用在参数上，对参数进行说明
    public ResponseEntity<List<ExpressCompany>> getExpressCompanyList() {
        List<ExpressCompany> list = expressCompanyService.getCompanyList();
        return ResponseEntity.ok(list);
    }

}
