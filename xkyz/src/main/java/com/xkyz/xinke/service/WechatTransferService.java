package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.*;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.WechatTransfer;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.util.MoneyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WechatTransferService {

    @Autowired
    WechatTransferMapper wechatTransferMapper;
    @Autowired
    UserService userService;

    public int addDeliverTask(String userToken,Double amount) {
        String openIdBySkey = userService.getOpenIdBySkey(userToken);
        if(StringUtils.isBlank(openIdBySkey)) throw new EmException(ExceptionEnums.INVALID_USER_TOKEN);
        WechatTransfer wechatTransfer = WechatTransfer.builder().openId(openIdBySkey).amount(amount).build();
        return wechatTransferMapper.insert(wechatTransfer);
    }

//    public WechatTransfer getWechatTransferByOpenId(String openId) {
//        WechatTransfer build = WechatTransfer.builder().openId(openId).build();
//        return wechatTransferMapper.selectOne(build);
//    }

    public BigDecimal getWechatTransferByOpenId(String openId) {
        Example example = new Example(WechatTransfer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openId", openId);
        List<WechatTransfer> wechatTransfers = wechatTransferMapper.selectByExample(example);
        Integer income=wechatTransfers.size()*2;
//        BigDecimal res=new BigDecimal("0");
//        for (WechatTransfer wechatTransfer : wechatTransfers) {
//            BigDecimal tmp = new BigDecimal(wechatTransfer.getAmount());
//            res=MoneyUtil.moneyAdd(res,tmp);
//        }
        BigDecimal res=new BigDecimal(income);
        return res;
    }

}
