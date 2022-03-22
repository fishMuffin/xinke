package com.xkyz.xinke.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnUser {
    private String token;
    private int role;
    private String portraitUrl;
    private String wxName;
}
