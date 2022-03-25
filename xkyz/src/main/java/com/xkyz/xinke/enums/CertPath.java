package com.xkyz.xinke.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CertPath {
    PROD_PATH("/Users/renyuying/IdeaProjects/xinke/xkyz/src/main/resources/cert/apiclient_cert.p12"),//发布测试
//    PROD_PATH("/root/xinke/resources/apiclient_cert.p12")//服务器使用


    ;
    private String name;
}
