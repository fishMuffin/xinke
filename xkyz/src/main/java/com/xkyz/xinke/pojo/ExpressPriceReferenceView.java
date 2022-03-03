package com.xkyz.xinke.pojo;

import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.ExpressPriceReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
public class ExpressPriceReferenceView {

    public ExpressPriceReferenceView(Integer id, String destinationProvince, String aboutTime, Integer firstKilogram, Integer perFromOneToThirty, Integer perFromThirty, Integer expressCompanyId, String expressCompanyName) {
        this.id = id;
        this.destinationProvince = destinationProvince;
        this.aboutTime = aboutTime;
        this.firstKilogram = firstKilogram;
        this.perFromOneToThirty = perFromOneToThirty;
        this.perFromThirty = perFromThirty;
        this.expressCompanyId = expressCompanyId;
        this.expressCompanyName = expressCompanyName;
    }

    public ExpressPriceReferenceView() {
    }

    private Integer id;
    public String destinationProvince;
    public String aboutTime;
    public Integer firstKilogram;
    public Integer perFromOneToThirty;
    public Integer perFromThirty;
    public Integer expressCompanyId;
    public String expressCompanyName;

}
