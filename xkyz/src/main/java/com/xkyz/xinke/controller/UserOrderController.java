package com.xkyz.xinke.controller;

import com.xkyz.xinke.common.UserOrderViewComparator;
import com.xkyz.xinke.common.UserOrderWithComViewComparator;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.IncomeView;
import com.xkyz.xinke.pojo.ReturnMSG;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.pojo.UserOrderWithCompanyView;
import com.xkyz.xinke.service.*;
import com.xkyz.xinke.util.MoneyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Api(tags = "用户订单API")
@RestController()
@RequestMapping("/sys/order")
public class UserOrderController {
    private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private WxService wxService;
    @Autowired
    private WechatTransferService wechatTransferService;
    @Autowired
    private SendWxMessageService sendWxMessageService;
    @Autowired
    private StorePointsService storePointsService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserAddressService userAddressService;

    //    userToken=35740a06-fe2f-4ef8-981b-d12101ec790c,
    @ApiOperation("创建订单")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/create")
    public ResponseEntity<ReturnMSG> addUserOrder(UserOrder userOrder) {
        logger.info("UserOrderController--addUserOrder--userOrder:" + userOrder.toString());
        int i = userOrderService.addUserOrder(userOrder);
//        String pointsName = storePointsService.getPointsNameById(userOrder.getPointsId());
//        String phoneNumber = userProfileService.getPhoneNumberByUserToken(userOrder.getUserToken());
//        Double price = userOrder.getPrice();
////        角色:1-商家,2-用户,3-揽收员
//        List<User> delivers = userService.getListByRole(3);
//        delivers.stream().forEach(s->{
//            String deliverName = userProfileService.getNameByUserToken(s.getSkey());
//            String sendRes = sendWxMessageService.pushMessageToDeliver(s.getOpenId(), pointsName, phoneNumber, price, deliverName);
//            logger.info("UserOrderController--addUserOrder0--sendRes:"+sendRes);
//        });

        return ResponseEntity.ok().body(new ReturnMSG("ok"));
    }


    @ApiOperation("取消订单")
    @PostMapping(value = "/cancel")
    public ResponseEntity<ReturnMSG> cancelUserOrder(@ApiParam("orderNo") String orderNo) {
        int i = userOrderService.cancelUserOrder(orderNo);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));
    }

    @ApiOperation("订单付款完成后改变订单状态")
    @PostMapping(value = "/finishPay")
    public ResponseEntity<ReturnMSG> finishOrder(@ApiParam("orderNo") String orderNo) {
        int i = userOrderService.finishOrder(orderNo);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));
    }

    @ApiOperation("获取订单信息")//作用在API方法上，对操作进行说明
    @GetMapping(value = "/get")
    public ResponseEntity<UserOrderView> getUserOrderByOrderNo(String orderNo) {
        UserOrderView userOrderView = userOrderService.getUserOrderByOrderNo(orderNo);
        return ResponseEntity.ok(userOrderView);
    }

    @ApiOperation("根据userToken和状态获取订单列表")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/list")
    public ResponseEntity<List<UserOrderView>> getUserOrderListByOpenId(@ApiParam("token") String token, @ApiParam("订单状态：1未结算，2已取消，3已发货") Integer status) {
        List<UserOrderView> list = userOrderService.getUserOrderListByOpenId(token, status);
        Collections.sort(list, new UserOrderViewComparator());
        return ResponseEntity.ok(list);
    }

    @ApiOperation("获取未揽收和已揽收列表,其中已揽收需要加揽收员deliverToken,未揽收列表该字段给空字符串")
    @PostMapping(value = "/deliverOrderList")
    public ResponseEntity<List<UserOrderWithCompanyView>> getUserOrderListByDeliverToken(@ApiParam("deliverToken") String deliverToken, @ApiParam("揽收状态：1.未揽收，2已揽收") Integer deliverStatus) {
        List<UserOrderWithCompanyView> list = userOrderService.getUserOrderListByDeliverToken(deliverToken, deliverStatus);
        Collections.sort(list, new UserOrderWithComViewComparator());
        return ResponseEntity.ok(list);
    }

    @ApiOperation("新任务下点击获取未揽收列表")
    @PostMapping(value = "/newUnReceivedList")
    public ResponseEntity<List<UserOrderWithCompanyView>> getNewList(@ApiParam("揽收状态：1.未揽收，2已揽收") Integer deliverStatus, @ApiParam("店铺ID") Integer pointsId) {
        List<UserOrderWithCompanyView> list = userOrderService.getNewList(deliverStatus, pointsId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据网点id获取订单列表")
    @PostMapping(value = "/getListByPointsId")
    public ResponseEntity<List<UserOrderWithCompanyView>> getListByPointsId(@ApiParam("pointsId") Integer pointsId) {
        List<UserOrderWithCompanyView> list = userOrderService.getListByPointsId(pointsId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("更新订单信息")
    @PostMapping(value = "/update")
    public ResponseEntity<ReturnMSG> updateUserOrder(
            @ApiParam("订单所需变更的信息：orderNo，图片url必填，") UserOrder userOrder) {
        //TODO 给用户通知
        logger.info("UserOrderController--updateUserOrder--userOrder:" + userOrder);
        String userOpenId = userService.getOpenIdBySkey(userOrder.getUserToken());
//        String deliverOpenId = userService.getOpenIdBySkey(userOrder.getDeliverToken());
        String pointsName = storePointsService.getPointsNameById(userOrder.getPointsId());
        String phoneNumber = userProfileService.getPhoneNumberByUserToken(userOrder.getUserToken());
//        String addressName = userAddressService.getUserAddrescsNameByAddressId(userOrder.getReceiveAddress());
        String deliverName = userProfileService.getNameByUserToken(userOrder.getDeliverToken());
        phoneNumber = StringUtils.isEmpty(phoneNumber) ? "" : phoneNumber;
        String s = sendWxMessageService.pushMessageToUser(userOpenId, pointsName, userOrder.getPrice(), deliverName,phoneNumber,userOrder.getOrderNo());
        logger.info("UserOrderController--updateUserOrder--pushMessageToUser:" + s);
        userOrderService.updateUserOrder(userOrder);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));

    }

    @ApiOperation("商家今日收益")
    @PostMapping(value = "/storeTodayIncome")
    public ResponseEntity<IncomeView> storeTodayIncome(
            @ApiParam("商家token") String userToken) {
        logger.info("userOrderController storeTodayIncome userToken:" + userToken);
        Integer pointsId = userService.getPointsOwnerByUserToken(userToken);
        if(pointsId==null) throw new EmException(ExceptionEnums.INVALID_POINTS_ID);
        IncomeView incomeAndCount = userOrderService.getIncomeAndCount(pointsId);
        Double incomeOfAll = incomeAndCount.getIncomeOfAll();
        BigDecimal incomeOfAllDecimal = new BigDecimal(incomeOfAll);
        List<String> list = userService.getListByPointId(pointsId);
        logger.info("userOrderController storeTodayIncome OpenIdList:" + list.toString());
        if (!list.isEmpty()) {
            //减去提现的
            for (String s : list) {
                BigDecimal transferDecimal = wechatTransferService.getWechatTransferByOpenId(s);
                logger.info("userOrderController storeTodayIncome reduce:" + transferDecimal);
                incomeOfAllDecimal = MoneyUtil.moneySub(incomeOfAllDecimal, transferDecimal);
            }
            String tmp = MoneyUtil.formatMoneyToTow(incomeOfAllDecimal);
            Double aDouble = Double.valueOf(tmp);
            incomeAndCount.setIncomeOfAll(aDouble);
        }
        return ResponseEntity.ok(incomeAndCount);
    }


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
