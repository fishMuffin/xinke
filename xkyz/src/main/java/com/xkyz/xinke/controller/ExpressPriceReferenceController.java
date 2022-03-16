package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.pojo.ExpressPriceReferenceView;
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

import java.util.Arrays;
import java.util.List;

@Api(tags = "快递公司价格参照表")
@RestController()
@RequestMapping("/sys/priceReference")
public class ExpressPriceReferenceController {
    @Autowired
    private ExpressPriceReferenceService expressPriceReferenceService;

    @ApiOperation("根据目的地返回各个快递公司的价格")
    @GetMapping(value = "/get")
    public ResponseEntity<List<ExpressPriceReferenceView>> getPrice(@ApiParam("目的地(务必提供正确完整的省份名称比如：湖北省)") String destination, @ApiParam("快递公司id") Integer companyId) {
        List<ExpressPriceReferenceView> price = expressPriceReferenceService.getPrice(destination, companyId);
        return ResponseEntity.ok(price);
    }

    @ApiOperation("导入数据专用（前端勿调用，后期删除）")
    @GetMapping(value = "/addList")
    public ResponseEntity<Integer> addList() {
        List<ExpressPriceReference> list = Arrays.asList(
//                京东快递
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("阜阳").aboutTime("次日达").firstKilogram(10).perFromOneToThirty(2).perFromThirty(3).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("安徽 江苏 上海 浙江").aboutTime("次日达(准北，嚼件3 衢州，温州，舟山除 外）").firstKilogram(12).perFromOneToThirty(2).perFromThirty(3).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("北京 河南 湖北 江西 山东 天津").aboutTime("2-4日（含当日揽收）").firstKilogram(15).perFromOneToThirty(5).perFromThirty(6).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("广东 广西 贵州 吉林 辽宁 宁夏 青海，重庆 福建 河北 湖南 山西 陕西 内蒙 四川 云南 甘肃").aboutTime("2-5日（含当日揽收）").firstKilogram(16).perFromOneToThirty(6).perFromThirty(7).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("海南 黑龙江 内蒙 四川 云南 甘肃").aboutTime("4-6日（含当日揽收）").firstKilogram(17).perFromOneToThirty(6).perFromThirty(7).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("新疆").aboutTime("6-7日（含当日揽收）").firstKilogram(20).perFromOneToThirty(10).perFromThirty(11).build(),
                ExpressPriceReference.builder().expressCompanyId(1).destinationProvince("西藏").aboutTime("8-10日（含当日揽收）").firstKilogram(24).perFromOneToThirty(12).perFromThirty(14).build()
//                德邦快递
//                ExpressPriceReference.builder().expressCompanyId(4).destinationProvince("阜阳 安徽").aboutTime("").firstKilogram(8).perFromOneToThirty(1).perFromThirty(0).build(),
//                ExpressPriceReference.builder().expressCompanyId(4).destinationProvince("江苏 浙江 上海").aboutTime("").firstKilogram(10).perFromOneToThirty(1).perFromThirty(0).build(),
//                ExpressPriceReference.builder().expressCompanyId(4).destinationProvince("").aboutTime("").firstKilogram(8).perFromOneToThirty(1).perFromThirty(0).build(),
//                ExpressPriceReference.builder().expressCompanyId(4).destinationProvince("阜阳 安徽").aboutTime("").firstKilogram(8).perFromOneToThirty(1).perFromThirty(0).build(),
//                ExpressPriceReference.builder().expressCompanyId(4).destinationProvince("阜阳 安徽").aboutTime("").firstKilogram(8).perFromOneToThirty(1).perFromThirty(0).build(),

        );
        int i = expressPriceReferenceService.addList(list);
        return ResponseEntity.ok(i);
    }

}
