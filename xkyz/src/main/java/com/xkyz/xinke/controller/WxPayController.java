package com.xkyz.xinke.controller;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.service.UserOrderService;
import com.xkyz.xinke.service.UserService;
import com.xkyz.xinke.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Api(tags = "微信支付接口管理")
@RestController
@RequestMapping("/sys/wxPay")
public class WxPayController {
    private final Logger logger = LoggerFactory.getLogger(WxPayController.class);
    @Autowired
    private WxService wxPayService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserService userService;
    /**
     * 统一下单接口
     */
    @ApiOperation(value = "统一下单", notes = "统一下单")
    @PostMapping("/unifiedOrder")
    public ResponseEntity<Map<String, String>> unifiedOrder(
            @ApiParam(value = "订单金额") @RequestParam Double amount, //TODO 先写死
            @ApiParam(value = "用户token") @RequestParam String userToken,
            @ApiParam(value = "订单号") @RequestParam String orderNo
    ) {
        try {
            logger.info("unifiedOrder--amout:",amount);
            int amountFen = (int) (amount * 100);//微信接口需要的是以分为单位
            String body = "新客驿站-统一下单";
            // 1、验证订单是否存在
            UserOrderView userOrderByOrderNo = userOrderService.getUserOrderByOrderNo(orderNo);
            if (userOrderByOrderNo == null) {
                throw new EmException(ExceptionEnums.USER_ORDER_NOT_EXIST);
            }
            String openId = userService.getOpenIdBySkey(userToken);
            if(StringUtils.isEmpty(openId)){
                throw new EmException(ExceptionEnums.INVALID_USER_TOKEN);
            }

            // 2、开始微信支付统一下单
            Map map = wxPayService.doUnifiedOrder(orderNo, amountFen, body,openId);
//            Map<String, String> stringStringMap = wxPayService.unifiedOrder(orderNo, amount, body);
            logger.info("unifiedOrder return data:",map.toString());
            return ResponseEntity.ok(map);//系统通用的返回结果集，见文章末尾
        } catch (Exception e) {
            logger.error("WxPayController:" + e.getMessage());
            throw new EmException(ExceptionEnums.UNIFIED_ORDER_FAILED);
        }
    }
//
//    /**
//     * 微信支付异步通知
//     */
//    @RequestMapping(value = "/notify")
//    public String payNotify(HttpServletRequest request) {
//        InputStream is = null;
//        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
//        try {
//            is = request.getInputStream();
//            // 将InputStream转换成String
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            xmlBack = wxPayService.notify(sb.toString());
//        } catch (Exception e) {
//            logger.error("微信手机支付回调通知失败：", e);
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return xmlBack;
//    }
//
//    @ApiOperation(value = "退款", notes = "退款")
//    @PostMapping("/refund")
//    public ResultMap refund(@ApiParam(value = "订单号") @RequestParam String orderNo,
//                            @ApiParam(value = "退款金额") @RequestParam double amount,
//                            @ApiParam(value = "退款原因") @RequestParam(required = false) String refundReason){
//
//        return wxPayService.refund(orderNo, amount, refundReason);
//    }

}
