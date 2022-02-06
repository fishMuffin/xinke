package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("快递公司实体类")
@Data
@Builder
@Table(name = "express_company")
public class ExpressCompany {

    public ExpressCompany() {
    }

    public ExpressCompany(Integer id, String companyName, String companyImageUrl, String arrival_time, Double first_price, Double additional_price) {
        this.id = id;
        this.companyName = companyName;
        this.companyImageUrl = companyImageUrl;
        this.arrival_time = arrival_time;
        this.first_price = first_price;
        this.additional_price = additional_price;
    }

    @ApiModelProperty("id")
    @Id
    private Integer id;
    @ApiModelProperty("快递公司名称")
    public String companyName;
    @ApiModelProperty("快递公司头像url")
    public String companyImageUrl;
    @ApiModelProperty("预估到达时间,暂定24小时制,比如11:00")
    public String arrival_time;
    @ApiModelProperty("首重价格")
    public Double first_price;
    @ApiModelProperty("续重价格")
    public Double additional_price;
}
//    CREATE TABLE `express_company`
//        (
//        `id`                int NOT NULL AUTO_INCREMENT,
//        `company_name`      varchar(100) DEFAULT NULL,
//        `company_image_url` varchar(100) DEFAULT NULL comment '头像url',
//        `arrival_time`      int(20)      DEFAULT NULL comment '预估到达时间',
//        `first_price`       DECIMAL(10, 2) comment '首重价格',
//        `additional_price`  DECIMAL(10, 2) comment '续重价格',
//        PRIMARY KEY (`id`)
//        ) ENGINE = InnoDB
//        DEFAULT CHARSET = utf8mb3;
