package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("用户实体类")
@Data
@Builder
public class UserProfile {

    public UserProfile(Integer id, String wx_name, String password, String skey, String phoneNumber, String portrait_url) {
        this.id = id;
        this.wx_name = wx_name;
        this.password = password;
        this.skey = skey;
        this.phoneNumber = phoneNumber;
        this.portrait_url = portrait_url;
    }

    public UserProfile() {
    }

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("微信名称")
    public String wx_name;
    @ApiModelProperty("密码")
    public String password;
    @ApiModelProperty("用户token")
    public String skey;
    @ApiModelProperty("用户电话号码")
    public String phoneNumber;
    @ApiModelProperty("头像url")
    public String portrait_url;
}
//    CREATE TABLE `user_profile`
//        (
//        `id`           int         NOT NULL AUTO_INCREMENT,
//        `phone_number` int          DEFAULT NULL,
//        `wx_name`      varchar(100) DEFAULT NULL,
//        `password`     varchar(40) NOT NULL,
//        `skey`         varchar(200) DEFAULT NULL,
//        `portrait_url` varchar(100) DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        ) ENGINE = InnoDB
//        DEFAULT CHARSET = utf8mb3;