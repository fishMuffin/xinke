package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.DeliverTaskMapper;
import com.xkyz.xinke.mapper.ExpressPriceReferenceMapper;
import com.xkyz.xinke.mapper.StorePointsMapper;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.ExpressPriceReference;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.DeliverTaskView;
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

    public List<ExpressPriceReference> getPrice(String destination) {
        //获取list
//        Example example = new Example(ExpressPriceReference.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("expressCompanyId", expressCompanyId);
        List<ExpressPriceReference> list = expressPriceReferenceMapper.selectAll();
//        List<ExpressPriceReference> list = expressPriceReferenceMapper.selectByExample(example);
        ExpressPriceReference res = ExpressPriceReference.builder().build();
        String desTmp = dataConvert(destination);
        List<ExpressPriceReference> resList=new ArrayList<>();
        for (ExpressPriceReference reference : list) {
            if (reference.getDestinationProvince().contains(desTmp)) {
                reference.setDestinationProvince(destination);
                resList.add(reference);
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
