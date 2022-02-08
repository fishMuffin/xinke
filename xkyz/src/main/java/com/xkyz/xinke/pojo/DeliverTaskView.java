package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.StorePoints;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliverTaskView {

    public DeliverTaskView() {
    }

    public DeliverTaskView(Integer taskId, String deliverToken, Integer expressAmount, Integer status, String ownerToken, StorePoints storePoints) {
        this.taskId = taskId;
        this.deliverToken = deliverToken;
        this.expressAmount = expressAmount;
        this.status = status;
        this.ownerToken = ownerToken;
        this.storePoints = storePoints;
    }

    private Integer taskId;
    private String deliverToken;
    private Integer expressAmount;
    private Integer status;
    private String ownerToken;
    private StorePoints storePoints;
}
