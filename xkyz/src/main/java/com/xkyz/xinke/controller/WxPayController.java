package com.xkyz.xinke.controller;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.service.UserOrderService;
import com.xkyz.xinke.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "微信支付接口管理")
@RestController
@RequestMapping("/sys/wxPay")
public class WxPayController{
    private final Logger logger = LoggerFactory.getLogger(WxPayController.class);
    @Autowired
    private WxService wxPayService;
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 统一下单接口
     */
    @ApiOperation(value = "统一下单", notes = "统一下单")
    @GetMapping("/unifiedOrder")
    public ResponseEntity<Map<String, String>> unifiedOrder(
//        @ApiParam(value = "订单金额") @RequestParam double amount, //TODO 先写死
//        @ApiParam(value = "用户token") @RequestParam String userToken,
//        @ApiParam(value = "订单号") @RequestParam String orderNo,
                                  ) {
        try {
            String userToken="824da98e-f39d-4932-b508-495e6c3b64ff";
            String orderNo="dsfdsfgdsgfdgfd";
            Double amount=100.34;
            String body="日用品";
            // 1、验证订单是否存在
            UserOrderView userOrderByOrderNo = userOrderService.getUserOrderByOrderNo(orderNo);
            if(userOrderByOrderNo==null){
                throw new EmException(ExceptionEnums.USER_ORDER_NOT_EXIST);
            }
            // 2、开始微信支付统一下单
            Map map = wxPayService.doUnifiedOrder(orderNo,amount,body);
            Map<String, String> stringStringMap = wxPayService.unifiedOrder(orderNo, amount, body);
            return ResponseEntity.ok(stringStringMap);//系统通用的返回结果集，见文章末尾
        } catch (Exception e) {
            logger.error("WxPayController:"+e.getMessage());
            throw new EmException(ExceptionEnums.RUNTIME_EXCEPTION);
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
