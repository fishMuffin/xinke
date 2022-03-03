//package com.xkyz.xinke.config;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClient;
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@ConfigurationProperties(prefix = "aliyun")
//@Data
//@Builder
//public class OssConfig {
//    public OssConfig() {
//    }
//
//    public OssConfig(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String urlPrefix) {
//        this.endpoint = endpoint;
//        this.accessKeyId = accessKeyId;
//        this.accessKeySecret = accessKeySecret;
//        this.bucketName = bucketName;
//        this.urlPrefix = urlPrefix;
//    }
//
//    private String endpoint;
//        private String accessKeyId;
//        private String accessKeySecret;
//        private String bucketName;
//        private String urlPrefix;
//
//        @Bean
//        public OSS oSSClient() {
//            return new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        }
//
//    }