package com.xkyz.xinke.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "em.upload")
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
