package com.xkyz.xinke.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CertPath {
//    PROD_PATH("src/main/resources/cert/apiclient_cert.p12"),//测试
    PROD_PATH("/root/xinke/resources/apiclient_cert.p12"),

    ;
    private String name;
}
