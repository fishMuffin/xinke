package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.service.UserOrderService;
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

@Api(tags = "订单API")
@RestController()
@RequestMapping("/sys/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @ApiOperation("创建订单")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/addUserOrder")
    public ResponseEntity<Integer> addUserOrder(UserOrder userOrder) {
        int i = userOrderService.addUserOrder(userOrder);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @ApiOperation("获取订单信息")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/getUserOrder")
    public ResponseEntity<UserOrder> getUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = userOrderService.getUserOrderByOrderNo(orderNo);
        return ResponseEntity.ok(userOrder);
    }

    @ApiOperation("更新订单信息")
    @PostMapping(value = "/updateUserOrder")
    public ResponseEntity<Boolean> updateUserOrder(
            @ApiParam("订单信息") UserOrder userOrder) {
        int i = userOrderService.updateUserOrder(userOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除订单")
    @GetMapping(value = "/deleteUserOrderByOrderNo")
    public ResponseEntity<Boolean> deleteUserOrderByOrderNo(@ApiParam("订单编号") String orderNo) {
        int i = userOrderService.deleteUserOrderByOrderNo(orderNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
