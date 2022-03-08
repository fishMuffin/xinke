package com.xkyz.xinke.util;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXConfigUtil implements WXPayConfig {
    private byte[] certData;
    public static final String APP_ID = "wx79444d769f2eeabd";
    public static final String SECRET_ID = "ed2d7b0b4ed7719a96f03d3abd2c7d85";
//    public static final String KEY = "你的api key不是appSecret";
    public static final String KEY = "Xkyz8888Xkyz8888Xkyz8888Xkyz8888";
    public static final String MCH_ID = "1620177995";



    public static final String certPath = "/root/xinke/resources/apiclient_cert.p12";

//    public static final String MCH_ID = "你的商户id";
//    1620177995

//    public WXConfigUtil() throws Exception {
//        String certPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"resources/cert/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的路径
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }


    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
