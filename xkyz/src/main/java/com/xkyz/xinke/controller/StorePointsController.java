package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.PointEntity;
import com.xkyz.xinke.pojo.ReturnUser;
import com.xkyz.xinke.pojo.UserAddressWithUserProfileView;
import com.xkyz.xinke.service.StorePointsService;
import com.xkyz.xinke.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "附近网店API")//作用在模块API类上，对API模块进行说明
@RestController()
@RequestMapping("/sys/points")
public class StorePointsController {
    @Autowired
    private StorePointsService storePointsService;
    @ApiOperation("根据坐标查询符合要求的附近网点")
    @PostMapping(value = "/list")
    //作用在参数上，对参数进行说明
    public ResponseEntity<List<StorePoints>> getPointsByXAndY(
            @ApiParam(name = "坐标经纬度:x为纬度，y为经度", required = true)PointEntity pointEntity) {
        List<StorePoints> list = storePointsService.getPointsByXAndY(pointEntity);
        return ResponseEntity.ok(list);
    }

}
