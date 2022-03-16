package com.xkyz.xinke.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.xkyz.xinke.enums.CertPath;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resources;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 配置我们自己的信息
 */
@Component
@ConfigurationProperties(prefix = "pay.wxpay.app")
@Data
@Builder
//public class WxPayAppConfig implements WXPayConfig {
public class WxPayAppConfig implements WXPayConfig {

    public WxPayAppConfig(byte[] certData) {
        this.certData = certData;
    }


    private byte[] certData;

    public WxPayAppConfig() throws Exception {
//        URL resource = Resources.class.getClassLoader().getResource("cert/apiclient_cert.p12");
//        String certPath = resource.getPath();
        File file = new File(CertPath.PROD_PATH.getName());//
//        File file = new File(certPath);
//        InputStream certStream = this.getClass().getResourceAsStream("/properties/basecom.properties");
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }


    public String getAppID() {
        return "wx79444d769f2eeabd";
    }

    public String getMchID() {
        return "1620177995";
    }

    public String getKey() {
        return "88888888888888888888888888888888";
    }
//    public String getCertPath() {
//        return "/root/xinke/resources/apiclient_cert.p12";
//    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
