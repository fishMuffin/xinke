package com.xkyz.xinke.service;

import com.xkyz.xinke.mapper.ExpressCompanyMapper;
import com.xkyz.xinke.mapper.StorePointsMapper;
import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.PointEntity;
import com.xkyz.xinke.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ExpressCompanyService {

    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;


    public List<ExpressCompany> getCompanyList() {
        List<ExpressCompany> list = expressCompanyMapper.selectAll();
        return list;
    }

    public ExpressCompany getCompanyById(Integer companyId){
        return expressCompanyMapper.selectByPrimaryKey(companyId);
    }

}
