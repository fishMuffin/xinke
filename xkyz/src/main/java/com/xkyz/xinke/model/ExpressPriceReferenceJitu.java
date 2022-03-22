package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("快递公司价格参照表_极兔")
@Data
@Builder
@Table(name = "express_price_reference_jitu")
public class ExpressPriceReferenceJitu {
    public ExpressPriceReferenceJitu() {
    }

    public ExpressPriceReferenceJitu(Integer id, String destinationProvince, Double onePointFiveKilogram, Double oneKilogram, Double twoKilogram, Double threeKilogram, Double perFromThreeToTenContinue, Double perFromTenFirst, Double perFromTenContinue, Integer expressCompanyId) {
        this.id = id;
        this.destinationProvince = destinationProvince;
        this.onePointFiveKilogram = onePointFiveKilogram;
        this.oneKilogram = oneKilogram;
        this.twoKilogram = twoKilogram;
        ThreeKilogram = threeKilogram;
        this.perFromThreeToTenContinue = perFromThreeToTenContinue;
        this.perFromTenFirst = perFromTenFirst;
        this.perFromTenContinue = perFromTenContinue;
        this.expressCompanyId = expressCompanyId;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    @ApiModelProperty("目标省份")
    public String destinationProvince;
    @ApiModelProperty("0.5公斤以内价格")
    public Double onePointFiveKilogram;
    @ApiModelProperty("1公斤以内价格")
    public Double oneKilogram;
    @ApiModelProperty("2公斤以内价格")
    public Double twoKilogram;
    @ApiModelProperty("3公斤以内价格")
    public Double ThreeKilogram;
    @ApiModelProperty("3-10公斤内每kg价格")
    public Double perFromThreeToTenContinue;
    @ApiModelProperty("10公斤以上首重")
    public Double perFromTenFirst;
    @ApiModelProperty("10公斤以上续重")
    public Double perFromTenContinue;
    @ApiModelProperty("快递公司id")
    public Integer expressCompanyId;
}
//    CREATE TABLE `express_price_reference_jitu` (
//        `id` int(11) NOT NULL AUTO_INCREMENT,
//        `destination_province` varchar(200) DEFAULT NULL,
//        `one_point_five_kilogram` decimal(10,2) NOT NULL,
//        `one_kilogram` decimal(10,2) NOT NULL,
//        `two_kilogram` decimal(10,2) NOT NULL,
//        `three_kilogram` decimal(10,2) NOT NULL,
//        `per_from_three_to_ten_continue` decimal(10,2) NOT NULL COMMENT '3-10千克续重',
//        `per_from_ten_first` decimal(10,2) NOT NULL COMMENT '10kg首重',
//        `per_from_ten_continue` decimal(10,2) NOT NULL COMMENT '10kg续重',
//        `express_company_id` int(11) NOT NULL COMMENT 'id',
//        PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
//        drop table express_price_reference_jitu;