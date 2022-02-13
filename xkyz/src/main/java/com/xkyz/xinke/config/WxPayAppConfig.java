package com.xkyz.xinke.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 配置我们自己的信息
 */
@Component
@ConfigurationProperties(prefix = "pay.wxpay.app")
@Data
@Builder
public class WxPayAppConfig implements WXPayConfig {
    public WxPayAppConfig() {
    }

    public WxPayAppConfig(String appID, String mchID, String key, String certPath, int httpConnectTimeoutMs, int httpReadTimeoutMs, String payNotifyUrl, String refundNotifyUrl) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
        this.certPath = certPath;
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
        this.httpReadTimeoutMs = httpReadTimeoutMs;
        this.payNotifyUrl = payNotifyUrl;
        this.refundNotifyUrl = refundNotifyUrl;
    }

    /**
     * appID
     */
    private String appID;

    /**
     * 商户号
     */
    private String mchID;

    /**
     * API 密钥
     */
    private String key;

    /**
     * API证书绝对路径 (本项目放在了 resources/cert/apiclient_cert.p12")
     */
    private String certPath;

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     */
    private int httpConnectTimeoutMs = 8000;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     */
    private int httpReadTimeoutMs = 10000;

    /**
     * 微信支付异步通知地址
     */
    private String payNotifyUrl;

    /**
     * 微信退款异步通知地址
     */
    private String refundNotifyUrl;

    /**
     * 获取商户证书内容（这里证书需要到微信商户平台进行下载）
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream() {
        InputStream certStream  =getClass().getClassLoader().getResourceAsStream(certPath);
        return certStream;
    }

}
