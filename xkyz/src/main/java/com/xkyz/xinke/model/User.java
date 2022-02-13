package com.xkyz.xinke.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("用户实体类")
@Data
@Builder
@NoArgsConstructor
@Table(name = "user")
public class User {
    public User(Integer id, String openId, String sessionKey, String skey, int role) {
        this.id = id;
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.skey = skey;
        this.role = role;
    }

    @ApiModelProperty("地址id")
    @Id
    private Integer id;
    @ApiModelProperty("open_id")
    public String openId;
    @ApiModelProperty("session_key")
    public String sessionKey;
    @ApiModelProperty("skey")
    public String skey;
    @ApiModelProperty("角色:1-商家,2-用户,3-揽收员")
    public int role;
}
//    create table user(
//        id int primary key AUTO_INCREMENT,
//        open_id varchar(40) DEFAULT NULL,
//    session_key varchar(40) DEFAULT NULL,
//    skey varchar(100) DEFAULT NULL
//)ENGINE = InnoDB
//        DEFAULT CHARSET = utf8mb3;