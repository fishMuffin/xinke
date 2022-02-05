package com.xkyz.xinke.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnMSG {
    public ReturnMSG() {
    }

    public ReturnMSG(String msg) {
        this.msg = msg;
    }

    private String msg;
}
