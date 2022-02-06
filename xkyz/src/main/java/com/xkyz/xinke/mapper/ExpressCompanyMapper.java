package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.StorePoints;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "expressCompanyMapper")
public interface ExpressCompanyMapper extends Mapper<ExpressCompany> {

}