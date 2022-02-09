package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("快递公司价格参照表")
@Data
@Builder
@Table(name = "express_price_reference")
public class ExpressPriceReference {

    public ExpressPriceReference() {
    }

    public ExpressPriceReference(Integer id, String destinationProvince, String aboutTime, Integer firstKilogram, Integer perFromOneToThirty, Integer perFromThirty, Integer expressCompanyId) {
        this.id = id;
        this.destinationProvince = destinationProvince;
        this.aboutTime = aboutTime;
        this.firstKilogram = firstKilogram;
        this.perFromOneToThirty = perFromOneToThirty;
        this.perFromThirty = perFromThirty;
        this.expressCompanyId = expressCompanyId;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    @ApiModelProperty("目标省份")

    public String destinationProvince;
    @ApiModelProperty("预计时效")
    public String aboutTime;
    @ApiModelProperty("一公斤以内价格")
    public Integer firstKilogram;
    @ApiModelProperty("1-30公斤内每kg价格")
    public Integer perFromOneToThirty;
    @ApiModelProperty("30kg以上每kg价格")
    public Integer perFromThirty;
    @ApiModelProperty("快递公司id")
    public Integer expressCompanyId;
}
//    create table express_price_reference
//        (
//                id            int primary key AUTO_INCREMENT,
//                destination_province      varchar(40)  DEFAULT NULL comment '目的省份',
//        about_time        varchar(40)  DEFAULT NULL comment '预计时效',
//        first_kilogram     int    NOT NULL comment '一公斤以内',
//        per_from_one_to_thirty int    NOT NULL comment '1-30公斤内每kg价格',
//        per_from_thirty int    NOT NULL comment '30kg以上每kg价格',
//        express_company_id     int    NOT NULL comment '快递公司id',
//        ) ENGINE = InnoDB
//        DEFAULT CHARSET = utf8mb3;