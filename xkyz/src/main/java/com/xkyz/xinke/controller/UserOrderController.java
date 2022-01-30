package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.service.UserOrderService;
import com.xkyz.xinke.service.WxService;
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

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Api(tags = "订单API")
@RestController()
@RequestMapping("/sys/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private WxService wxService;

    @ApiOperation("创建订单")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/create")
    public ResponseEntity<Integer> addUserOrder(UserOrder userOrder) {
        int i = userOrderService.addUserOrder(userOrder);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @ApiOperation("获取订单信息")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/get")
    public ResponseEntity<UserOrder> getUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = userOrderService.getUserOrderByOrderNo(orderNo);
        return ResponseEntity.ok(userOrder);
    }

    @ApiOperation("根据OpenId获取订单列表")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/list")
    public ResponseEntity<List<UserOrder>> getUserOrderListByOpenId(String token) {
        List<UserOrder> list = userOrderService.getUserOrderListByOpenId(token);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("更新订单信息")
    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> updateUserOrder(
            @ApiParam("订单编号") String orderNo, @ApiParam("订单所需变更的状态") Integer status) {
        int i = userOrderService.updateUserOrder(orderNo, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("商家今日收益")
    @PostMapping(value = "/storeTodayIncome")
    public ResponseEntity<Double> storeTodayIncome(
            @ApiParam("商家token") String token) {
        Double income = userOrderService.getStoreTodayIncome(token);
        return ResponseEntity.ok(income);
    }
//    @ApiOperation("删除订单")
//    @GetMapping(value = "/delete")
//    public ResponseEntity<Boolean> deleteUserOrderByOrderNo(@ApiParam("订单编号") String orderNo) {
//        int i = userOrderService.deleteUserOrderByOrderNo(orderNo);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PostMapping("/notify")
    @ApiOperation("微信支付通知")
    public String wxPayNotify(HttpServletRequest request) {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxService.payBack(resXml);
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }

}
