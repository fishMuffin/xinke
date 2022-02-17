package com.xkyz.xinke.util;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyWxPayUtils extends WXPay {
    public MyWxPayUtils(WXPayConfig config) {
        super(config);
    }

    public MyWxPayUtils(WXPayConfig config, WXPayConstants.SignType signType) {
        super(config, signType);
    }

    public MyWxPayUtils(WXPayConfig config, WXPayConstants.SignType signType, boolean useSandbox) {
        super(config, signType, useSandbox);
    }



    @Override
    public Map<String, String> unifiedOrder(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
//        if (super.useSandbox) {
//            url = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
//        } else {
            url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//        }
        Map<String, String> stringStringMap = super.fillRequestData(reqData);
        HashMap<String, String> objectObjectHashMap = new HashMap<>(stringStringMap);
        String respXml = super.requestWithoutCert(url, objectObjectHashMap, connectTimeoutMs, readTimeoutMs);
        return super.processResponseXml(respXml);
    }
}
