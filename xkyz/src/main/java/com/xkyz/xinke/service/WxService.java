package com.xkyz.xinke.service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xkyz.xinke.util.WXConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saodiseng
 * @date 2019/2/18
 */
@Service
@Slf4j
public class WxService {
    public static final String SPBILL_CREATE_IP = "服务器ip地址";
    public static final String NOTIFY_URL = "回调接口地址";
    public static final String TRADE_TYPE_APP = "APP";


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

//    public ResultMap unifiedOrder(String orderNo, double amount, String body) {
//        Map<String, String> returnMap = new HashMap<>();
//        Map<String, String> responseMap = new HashMap<>();
//        Map<String, String> requestMap = new HashMap<>();
//        try {
//            WXPay wxpay = new WXPay(wxPayAppConfig);
//            requestMap.put("body", body);                                     // 商品描述
//            requestMap.put("out_trade_no", orderNo);                          // 商户订单号
//            requestMap.put("total_fee", String.valueOf((int)(amount*100)));   // 总金额
//            requestMap.put("spbill_create_ip", HttpContextUtils.getIpAddr()); // 终端IP
//            requestMap.put("trade_type", "APP");                              // App支付类型
//            requestMap.put("notify_url", wxPayAppConfig.getPayNotifyUrl());   // 接收微信支付异步通知回调地址
//            Map<String, String> resultMap = wxpay.unifiedOrder(requestMap);
//            //获取返回码
//            String returnCode = resultMap.get("return_code");
//            String returnMsg = resultMap.get("return_msg");
//            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
//            if ("SUCCESS".equals(returnCode)) {
//                String resultCode = resultMap.get("result_code");
//                String errCodeDes = resultMap.get("err_code_des");
//                if ("SUCCESS".equals(resultCode)) {
//                    responseMap = resultMap;
//                }
//            }
//            if (responseMap == null || responseMap.isEmpty()) {
//                return ResultMap.error("获取预支付交易会话标识失败");
//            }
//            // 3、签名生成算法
//            Long time = System.currentTimeMillis() / 1000;
//            String timestamp = time.toString();
//            returnMap.put("appid", wxPayAppConfig.getAppID());
//            returnMap.put("partnerid", wxPayAppConfig.getMchID());
//            returnMap.put("prepayid", responseMap.get("prepay_id"));
//            returnMap.put("noncestr", responseMap.get("nonce_str"));
//            returnMap.put("timestamp", timestamp);
//            returnMap.put("package", "Sign=WXPay");
//            returnMap.put("sign", WXPayUtil.generateSignature(returnMap, wxPayAppConfig.getKey()));//微信支付签名
//            return ResultMap.ok().put("data", returnMap);
//        } catch (Exception e) {
//            logger.error("订单号：{}，错误信息：{}", orderNo, e.getMessage());
//            return ResultMap.error("微信支付统一下单失败");
//        }
//    }

    public Map doUnifiedOrder(String orderNo,Double amount,String body) throws Exception {


        try {
            WXConfigUtil config = new WXConfigUtil();
            WXPay wxpay = new WXPay(config);
            Map<String, String> data = new HashMap<>();
            //生成商户订单号，不可重复
            data.put("appid", config.getAppID());
            data.put("mch_id", config.getMchID());
            data.put("nonce_str", WXPayUtil.generateNonceStr());
//            String body = "订单支付";
            data.put("body", body);
            data.put("out_trade_no", orderNo);
            data.put("total_fee", "1");
            //自己的服务器IP地址
            data.put("spbill_create_ip", SPBILL_CREATE_IP);
            //异步通知地址（请注意必须是外网）
            data.put("notify_url", NOTIFY_URL);
            //交易类型
            data.put("trade_type", TRADE_TYPE_APP);
            //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
            data.put("attach", "");
            data.put("sign", WXPayUtil.generateSignature(data, config.getKey(),
                    WXPayConstants.SignType.MD5));
            //使用官方API请求预付订单
            Map<String, String> response = wxpay.unifiedOrder(data);
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