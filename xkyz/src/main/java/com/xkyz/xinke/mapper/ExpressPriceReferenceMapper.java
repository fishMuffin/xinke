package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressPriceReference;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@Component(value = "expressPriceReferenceMapper")
public interface ExpressPriceReferenceMapper extends Mapper<ExpressPriceReference> {

}