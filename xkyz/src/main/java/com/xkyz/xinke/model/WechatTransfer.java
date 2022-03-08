package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("微信提现实体类")
@Data
@Builder
@Table(name = "wechat_transfer")
public class WechatTransfer {
    public WechatTransfer() {
    }

    public WechatTransfer(Integer id, Double amount, String openId) {
        this.id = id;
        this.amount = amount;
        this.openId = openId;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    @ApiModelProperty("提现金额")
    private Double amount;
    @ApiModelProperty("openId")
    private String openId;

}
