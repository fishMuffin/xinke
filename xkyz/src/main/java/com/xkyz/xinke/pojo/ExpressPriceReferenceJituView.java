package com.xkyz.xinke.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
public class ExpressPriceReferenceJituView {
    public ExpressPriceReferenceJituView() {
    }

    public ExpressPriceReferenceJituView(Integer id, String destinationProvince, Double onePointFiveKilogram, Double oneKilogram, Double twoKilogram, Double threeKilogram, Double perFromThreeToTenContinue, Double perFromTenFirst, Double perFromTenContinue, Integer expressCompanyId, String expressCompanyName) {
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
        this.expressCompanyName = expressCompanyName;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    private String destinationProvince;
    private Double onePointFiveKilogram;
    private Double oneKilogram;
    private Double twoKilogram;
    private Double ThreeKilogram;
    private Double perFromThreeToTenContinue;
    private Double perFromTenFirst;
    private Double perFromTenContinue;
    private Integer expressCompanyId;
    private String expressCompanyName;

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