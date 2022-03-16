package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.DeliverTaskMapper;
import com.xkyz.xinke.mapper.ExpressCompanyMapper;
import com.xkyz.xinke.mapper.ExpressPriceReferenceMapper;
import com.xkyz.xinke.mapper.StorePointsMapper;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.pojo.ExpressPriceReferenceView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpressPriceReferenceService {

    @Autowired
    ExpressPriceReferenceMapper expressPriceReferenceMapper;
    @Autowired
    ExpressCompanyService expressCompanyService;

    public List<ExpressPriceReferenceView> getPrice(String destination, Integer companyId) {
        //获取list
        ExpressCompany company = expressCompanyService.getCompanyById(companyId);
        Example example = new Example(ExpressPriceReference.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("expressCompanyId", companyId);
        List<ExpressPriceReference> list = expressPriceReferenceMapper.selectByExample(example);
        String desTmp = dataConvert(destination);
        List<ExpressPriceReferenceView> resList=new ArrayList<>();
        for (ExpressPriceReference reference : list) {
            if (reference.getDestinationProvince().contains(desTmp)) {
                reference.setDestinationProvince(destination);
                ExpressPriceReferenceView expressPriceReferenceView = ExpressPriceReferenceView.builder()
                        .expressCompanyId(reference.getExpressCompanyId())
                        .aboutTime(reference.getAboutTime())
                        .destinationProvince(reference.getDestinationProvince())
                        .expressCompanyName(company.getCompanyName())
                        .firstKilogram(reference.getFirstKilogram())
                        .id(reference.getId())
                        .perFromOneToThirty(reference.getPerFromOneToThirty())
                        .perFromThirty(reference.getPerFromThirty())
                        .build();
                resList.add(expressPriceReferenceView);
            }
        }
        return resList;
    }


    public int addList(List<ExpressPriceReference> list){
        int i = expressPriceReferenceMapper.insertList(list);
        return i;
    }


    private String dataConvert(String destination) {
        if (destination.contains("市")) {
            int i = destination.indexOf("市");
            return destination.substring(0,i);
        } else if (destination.contains("省")) {
            int i = destination.indexOf("省");
            return destination.substring(0,i);
        } else {
            return destination;
        }
    }


}
