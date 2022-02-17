package com.xkyz.xinke.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xkyz.xinke.config.WxPayAppConfig;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.util.MyWxPayUtils;
import com.xkyz.xinke.util.WXConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class WxService {
    private final Logger logger = LoggerFactory.getLogger(WxService.class);
    public static final String SPBILL_CREATE_IP = "118.31.0.252";//服务器ip地址
    public static final String NOTIFY_URL = "https://www.weixin.qq.com/wxpay/pay.php";// "回调接口地址";
    public static final String TRADE_TYPE_APP = "APP";//交易类型

    public static final String APP_ID = "wx79444d769f2eeabd";
    public static final String SECRET_ID = "ed2d7b0b4ed7719a96f03d3abd2c7d85";
    //    public static final String KEY = "你的api key不是appSecret";
    public static final String KEY = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String MCH_ID = "1620177995";
    public static final String CERT_PATH = "resources/cert/apiclient_cert.p12";


    public String payBack(String resXml) {
        WXConfigUtil config = null;
        try {
            config = new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(resXml);         // 调用官方SDK转换成map类型数据
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {//验证签名是否有效，有效则进一步处理

                String return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//商户订单号
                if (return_code.equals("SUCCESS")) {
                    if (out_trade_no != null) {
                        // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户的订单状态从退款改成支付成功
                        // 注意特殊情况：微信服务端同样的通知可能会多次发送给商户系统，所以数据持久化之前需要检查是否已经处理过了，处理了直接返回成功标志
                        //业务数据持久化
                        log.info("微信手机支付回调成功订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        log.info("微信手机支付回调失败订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }
                return xmlBack;
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                //失败的数据要不要存储？
                log.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            log.error("手机支付回调通知失败", e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }

    public Map<String, String> unifiedOrder(String orderNo, Integer amount, String body) {
        Map<String, String> returnMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();
        Map<String, String> requestMap = new TreeMap<>();
        try {
            WxPayAppConfig config = new WxPayAppConfig();
            WXPay wxpay = new WXPay(config);
            requestMap.put("body", body);                                     // 商品描述
            requestMap.put("out_trade_no", orderNo);                          // 商户订单号
            requestMap.put("total_fee", String.valueOf(amount));   // 总金额
            requestMap.put("spbill_create_ip", SPBILL_CREATE_IP); // 终端IP
            requestMap.put("trade_type", "APP");                              // App支付类型
            requestMap.put("notify_url", NOTIFY_URL);   // 接收微信支付异步通知回调地址

//            Map<String, String> data = new HashMap<String, String>();
//            data.put("body", "腾讯充值中心-QQ会员充值");
//            data.put("out_trade_no", "2016090910595900000012");
//            data.put("device_info", "");
//            data.put("fee_type", "CNY");
//            data.put("total_fee", "1");
//            data.put("spbill_create_ip", "123.12.12.123");
//            data.put("notify_url", "http://www.example.com/wxpay/notify");
//            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
//            data.put("product_id", "12");
//            MyWxPayUtils myWxPayUtils = new MyWxPayUtils(config);
//            Map<String, String> resultMap = myWxPayUtils.unifiedOrder(requestMap,config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs());
            Map<String, String> resultMap = wxpay.unifiedOrder(requestMap);
            //获取返回码
            String returnCode = resultMap.get("return_code");
            String returnMsg = resultMap.get("return_msg");
            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = resultMap.get("result_code");
                String errCodeDes = resultMap.get("err_code_des");
                if ("SUCCESS".equals(resultCode)) {
                    responseMap = resultMap;
                }
            }
            if (responseMap == null || responseMap.isEmpty()) {
                throw new EmException(ExceptionEnums.RUNTIME_EXCEPTION);
            }
            // 3、签名生成算法
            Long time = System.currentTimeMillis() / 1000;
            String timestamp = time.toString();
            returnMap.put("appid", config.getAppID());
            returnMap.put("partnerid", config.getMchID());
            returnMap.put("prepayid", responseMap.get("prepay_id"));
            returnMap.put("noncestr", responseMap.get("nonce_str"));
            returnMap.put("timestamp", timestamp);
            returnMap.put("package", "Sign=WXPay");
            returnMap.put("sign", WXPayUtil.generateSignature(returnMap, config.getKey()));//微信支付签名
//            return ResultMap.ok().put("data", returnMap);
            return returnMap;
        } catch (Exception e) {
            logger.error("订单号：{}，错误信息：{}", orderNo, e.getMessage());
            throw new EmException(ExceptionEnums.UNIFIED_ORDER_FAILED);
//            return ResultMap.error("微信支付统一下单失败");
        }
    }

    public Map doUnifiedOrder(String orderNo, Integer amount, String body) throws Exception {


        try {
            WXConfigUtil config = new WXConfigUtil();
            WXPay wxpay = new WXPay(config);
            Map<String, String> data = new TreeMap<>();

//            requestMap.put("body", body);                                     // 商品描述
//            requestMap.put("out_trade_no", orderNo);                          // 商户订单号
//            requestMap.put("total_fee", String.valueOf((int)(amount*100)));   // 总金额
//            requestMap.put("spbill_create_ip", HttpContextUtils.getIpAddr()); // 终端IP
//            requestMap.put("trade_type", "APP");                              // App支付类型
//            requestMap.put("notify_url", wxPayAppConfig.getPayNotifyUrl());   // 接收微信支付异步通知回调地址
//            Map<String, String> resultMap = wxpay.unifiedOrder(requestMap);

            //生成商户订单号，不可重复
            data.put("appid", APP_ID);
            data.put("mch_id", MCH_ID);
            data.put("nonce_str", WXPayUtil.generateNonceStr());
//            String body = "订单支付";
            data.put("body", body);
            data.put("out_trade_no", orderNo);
            data.put("total_fee", String.valueOf(amount));//总金额
            //自己的服务器IP地址
            data.put("spbill_create_ip", SPBILL_CREATE_IP);
            //异步通知地址（请注意必须是外网）
            data.put("notify_url", NOTIFY_URL);
            //交易类型
            data.put("trade_type", TRADE_TYPE_APP);
            //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
//            data.put("attach", "");
//            data.put("sign", WXPayUtil.generateSignature(data, KEY,
//                    WXPayConstants.SignType.MD5));
            //使用官方API请求预付订单
//            wxpay.requestWithoutCert()
//                        MyWxPayUtils myWxPayUtils = new MyWxPayUtils(config);
//            Map<String, String> response = myWxPayUtils.unifiedOrder(data,config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs());
            Map<String, String> reqMap=new LinkedHashMap<>(data);
            reqMap.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
//            data.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
//            Map<String, String> response = wxpay.unifiedOrder(data);
            String respXml = wxpay.requestWithoutCert("https://api.mch.weixin.qq.com/pay/unifiedorder", reqMap, config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs());
            Map<String, String> response = wxpay.processResponseXml(respXml);
//            return this.processResponseXml(respXml);
            if ("SUCCESS".equals(response.get("return_code"))) {//主要返回以下5个参数
                Map<String, String> param = new HashMap<>();
                param.put("appid", config.getAppID());
                param.put("partnerid", response.get("mch_id"));
                param.put("prepayid", response.get("prepay_id"));
                param.put("package", "Sign=WXPay");
                param.put("noncestr", WXPayUtil.generateNonceStr());
                param.put("timestamp", System.currentTimeMillis() / 1000 + "");
                param.put("sign", WXPayUtil.generateSignature(param, config.getKey(),
                        WXPayConstants.SignType.MD5));
                return param;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("下单失败");
        }
        throw new Exception("下单失败");
    }
}