package com.xkyz.xinke.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.model.TransferModel;
import com.jfinal.kit.StrKit;
import com.xkyz.xinke.enums.CertPath;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.service.UserOrderService;
import com.xkyz.xinke.service.UserService;
import com.xkyz.xinke.service.WxService;
import com.xkyz.xinke.util.WXConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
//    public static final String CERT_PATH = "/root/xinke/resources/apiclient_cert.p12";

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
            logger.info("unifiedOrder--amout:{}",amount);
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
            logger.info("unifiedOrder return data:{}",map.toString());
            return ResponseEntity.ok(map);//系统通用的返回结果集，见文章末尾
        } catch (Exception e) {
            logger.error("WxPayController:" + e.getMessage());
            throw new EmException(ExceptionEnums.UNIFIED_ORDER_FAILED);
        }
    }


    /**
     * 企业付款到零钱
     */
    @ApiOperation(value = "提现", notes = "提现")
    @PostMapping("/transfer")
//    @RequestMapping(value = "/transfer", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
    public ResponseEntity<String> transfer(@RequestParam("openId") String openId) {

        WXConfigUtil config = new WXConfigUtil();
//        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }


        Map<String, String> params = TransferModel.builder()
                .mch_appid(config.getAppID())
                .mchid(config.getMchID())
                .nonce_str(WXPayUtil.generateNonceStr())
                .partner_trade_no(WxPayKit.generateStr())
                .openid(openId)
                .check_name("NO_CHECK")
                .amount("100")
                .desc("新客驿站企业付款")
//                .spbill_create_ip(ip)
                .build()
                .createSign(config.getKey(), SignType.MD5, false);

        // 提现
        String transfers = WxPayApi.transfers(params, CertPath.PROD_PATH.getName(), config.getMchID());
        logger.info("提现结果:" + transfers);
        Map<String, String> map = WxPayKit.xmlToMap(transfers);
        String returnCode = map.get("return_code");
        String resultCode = map.get("result_code");
        if (!(WxPayKit.codeIsOk(returnCode) && WxPayKit.codeIsOk(resultCode))) {
            // 提现失败

            logger.info("WxPayController_transfer:"+map);
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(transfers);
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
