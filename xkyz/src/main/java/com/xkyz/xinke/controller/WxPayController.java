//package com.xkyz.xinke.controller;
//
//import com.xkyz.xinke.service.WxService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@Api(tags = "微信支付接口管理")
//@RestController
//@RequestMapping("/wxPay")
//public class WxPayController{
//
//    @Autowired
//    private WxService wxPayService;
//
//    /**
//     * 统一下单接口
//     */
//    @ApiOperation(value = "统一下单", notes = "统一下单")
//    @GetMapping("/unifiedOrder")
//    public ResponseEntity<Map> unifiedOrder(
//        @ApiParam(value = "订单号") @RequestParam String orderNo,
//        @ApiParam(value = "订单金额") @RequestParam double amount,
//        @ApiParam(value = "商品名称") @RequestParam String body,
//                                  HttpServletRequest request) {
//        try {
//            // 1、验证订单是否存在
//
//            // 2、开始微信支付统一下单
//            Map map = wxPayService.doUnifiedOrder(orderNo,amount,body);
//            ResultMap resultMap = wxPayService.unifiedOrder(orderNo, orderNo, body);
//            return resultMap;//系统通用的返回结果集，见文章末尾
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return ResultMap.error("运行异常，请联系管理员");
//        }
//    }
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
//
//}
