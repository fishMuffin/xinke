package com.xkyz.xinke.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "hah.upload")
public class UploadVideoProperties {
    private String baseName;
    private List<String> allowTypes;
}
