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
    public static final String TRADE_TYPE_APP = "JSAPI";//交易类型

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

    public Map doUnifiedOrder(String orderNo, Integer amount, String body, String openId) throws Exception {


        WXConfigUtil config = new WXConfigUtil();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new TreeMap<>();

        //生成商户订单号，不可重复
        data.put("appid", APP_ID);
        data.put("mch_id", MCH_ID);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
//            String body = "订单支付";
        data.put("body", body);
        data.put("openid", openId);
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
        Map<String, String> reqMap = new LinkedHashMap<>(data);
        reqMap.put("sign", WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5));
        Map<String, String> response = wxpay.unifiedOrder(data);
//        result_code -> FAIL
        if ("SUCCESS".equals(response.get("return_code")) && (!"FAIL".equals(response.get("result_code")))) {//主要返回以下5个参数
            Map<String, String> param = new HashMap<>();
            String prepayId = response.get("prepay_id");
            // 组装参数package_str 为什么这样？ 因为二次签名微信规定这样的格式
            String package_str = "prepay_id=" + prepayId;
            param.put("appId", config.getAppID());
//            param.put("mch_id", response.get("mch_id"));
//            param.put("prepay_id", response.get("prepay_id"));

//            param.put("prepayid", response.get("prepay_id"));
            param.put("package", package_str);

            param.put("nonceStr", WXPayUtil.generateNonceStr());
            param.put("signType", WXPayConstants.SignType.MD5.name());
            param.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            Map<String, String> tmpParam = new LinkedHashMap<>(param);
            param.put("sign", WXPayUtil.generateSignature(tmpParam, config.getKey(),
                    WXPayConstants.SignType.MD5));
            param.put("partnerid", prepayId);
            param.put("packageValue", "Sign=WXPay");
            return param;


//            String prepayId = response.get("prepay_id");
//            63         // 组装参数package_str 为什么这样？ 因为二次签名微信规定这样的格式
//            64         String package_str = "prepay_id="+prepayId;
//            65         Map<String,String> payParameters = new HashMap<>();
//            66         long epochSecond = Instant.now().getEpochSecond();
//            67         payParameters.put("appId",WxConfig.appId);
//            68         payParameters.put("nonceStr", WxUtil.generateNonceStr());
//            69         payParameters.put("package", package_str);
//            70         payParameters.put("signType", SignType.MD5.name());
//            71         payParameters.put("timeStamp", String.valueOf(epochSecond));
//            72         // 二次签名
//            73         payParameters.put("paySign", WxUtil.generateSignature(payParameters, WxConfig.key, SignType.MD5));
//            74         // 返回签名后的map
//            75         return payParameters;

        }
        return response;
    }


////    @Autowired
////    WeixinMiniProgramPayProperties weixinPayProperties;
//
////    @Override
//    public Map enterprice2User(String logKey, String orderNumber, String openId, String fee, String body) throws Exception
//    {
//        //1.参数封装
//        Map param = new HashMap();
//        param.put("mch_appid", weixinPayProperties.getAppid());// 商户账号appid
//        param.put("mchid", weixinPayProperties.getMchId());// 商户
////        param.put("mch_id", weixinPayProperties.getMchId());// 商户
//        param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
//        param.put("partner_trade_no", orderNumber);// 交易订单号
////        param.put("out_trade_no", orderNumber);// 交易订单号
//        param.put("openid", openId); // 用户openid
//        param.put("check_name", "NO_CHECK"); // 校验用户姓名选项
//        param.put("amount", fee); // 金额
////        param.put("total_fee", fee); // 金额
//        param.put("desc", body);
////        param.put("body", body); // 商品描述
//        param.put("spbill_create_ip", weixinPayProperties.getSpbillCreateIp());
//
//        logger.info(logKey + " weixinPayProperties" + weixinPayProperties);
//        String sign = WXPayUtil.generateSignature(param, weixinPayProperties.getPartnerkey());
//        param.put("sign", sign);
//        logger.info(logKey + " weixinService#enterprice2User:" + param);
//
////        HttpClient httpClient = new HttpClient(weixinPayProperties.getWechatTransfersUrl());
////        httpClient.setHttps(true);
////        httpClient.setXmlParam(xml2String(param));
////        httpClient.post();
////
////        String xmlResult = httpClient.getContent();
////        Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
//
//        String xmlResult = WXHttpCertUtils.doPost(weixinPayProperties.getWechatTransfersUrl(), xml2String(param));
//        Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
//        logger.info(logKey + " weixinService#enterprice2User.wxpay.mp.result:" + mapResult);
//
//        Map<String, String> result = new HashMap<>();
//        result.put("code", "500");
//        result.put("message", "系统错误");
//
//        // {mchid=1520737191, mch_appid=wx2f030dd8ebda3a63, err_code=NOTENOUGH, return_msg=支付失败, result_code=FAIL, err_code_des=余额不足, return_code=SUCCESS}
//        // 转账成功
//        if("SUCCESS".equalsIgnoreCase(mapResult.get("result_code")))
//        {
//            result.put("code", "200");
//            result.put("message", "支付成功");
//        }
//        // 转帐失败
//        else if ("FAIL".equalsIgnoreCase(mapResult.get("result_code")))
//        {
//            result.put("code", "500");
//            // 系统错误需要重试[请先调用查询接口，查看此次付款结果，如结果为不明确状态（如订单号不存在），请务必使用原商户订单号进行重试。
//            if ("SYSTEMERROR".equalsIgnoreCase(mapResult.get("err_code")))
//            {
////                result.put("message", "支付成功");]
//            }
//            // 金额超限
//            else if("AMOUNT_LIMIT".equalsIgnoreCase(mapResult.get("err_code")))
//            {
//                result.put("message", "金额超限");
//            }
//            // 余额不足
//            else if("NOTENOUGH".equalsIgnoreCase(mapResult.get("err_code")))
//            {
////                result.put("message", "支付成功");
//            }
//            // 超过频率限制，请稍后再试。
//            else if("FREQ_LIMIT".equalsIgnoreCase(mapResult.get("err_code")))
//            {
//                result.put("message", "超过频率限制，请稍后再试。");
//            }
//            // 已经达到今日付款总额上限/已达到付款给此用户额度上限
//            else if("MONEY_LIMIT".equalsIgnoreCase(mapResult.get("err_code")))
//            {
//                result.put("message", "已经达到今日付款总额上限");
//            }
//            // 无法给非实名用户付款
//            else if ("V2_ACCOUNT_SIMPLE_BAN".equalsIgnoreCase(mapResult.get("err_code")))
//            {
//                result.put("message", "无法给非实名用户付款");
//            }
//            // 该用户今日付款次数超过限制,如有需要请登录微信支付商户平台更改API安全配置
//            else if("SENDNUM_LIMIT".equalsIgnoreCase(mapResult.get("err_code")))
//            {
//                result.put("message", "今日付款次数超过限制");
//            }
//        }
//        else
//        {
//            // 系统错误
//            result.put("code", "500");
//            result.put("message", "系统错误");
//        }
//
//        return result;
}