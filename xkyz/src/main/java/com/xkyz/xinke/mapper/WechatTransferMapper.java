package com.xkyz.xinke.mapper;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.WechatTransfer;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@Component(value = "wechatTransferMapper")
public interface WechatTransferMapper extends Mapper<WechatTransfer> {

}