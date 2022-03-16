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
@Table(name = "user")
public class User {
    public User(Integer id, String openId, String sessionKey, String skey, Integer role, Integer pointId) {
        this.id = id;
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.skey = skey;
        this.role = role;
        this.pointId = pointId;
    }

    public User() {
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
    public Integer role;
    @ApiModelProperty("网点ID")
    public Integer pointId;
}
