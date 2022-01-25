package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.UserOrder;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@Component(value = "deliverTaskMapper")
public interface DeliverTaskMapper extends Mapper<DeliverTask> {

}