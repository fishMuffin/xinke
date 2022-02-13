package com.xkyz.xinke.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PointEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 纬度
     */
    public Double x;
    /**
     * 经度
     */
    public Double y;

    public PointEntity() {}

    public PointEntity(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
