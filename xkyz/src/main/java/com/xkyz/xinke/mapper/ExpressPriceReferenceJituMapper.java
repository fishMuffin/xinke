package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.ExpressPriceReferenceJitu;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


@Component(value = "ExpressPriceReferenceMapperJitu")
public interface ExpressPriceReferenceJituMapper extends Mapper<ExpressPriceReferenceJitu>, InsertListMapper<ExpressPriceReferenceJitu> {

}