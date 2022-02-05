package com.xkyz.xinke.service;

import com.vividsolutions.jts.geom.Point;
import com.xkyz.xinke.mapper.StorePointsMapper;
import com.xkyz.xinke.mapper.UserAddressMapper;
import com.xkyz.xinke.mapper.UserProfileMapper;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.pojo.PointEntity;
import com.xkyz.xinke.pojo.UserAddressWithUserProfileView;
import com.xkyz.xinke.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorePointsService {

    @Autowired
    private StorePointsMapper storePointsMapper;

    private static final Integer RANGE = 3; //返回条数


    public List<StorePoints> getPointsByXAndY(PointEntity pointEntity) {

        //1.获取所有网点数据
        List<StorePoints> storePoints = storePointsMapper.selectAll();
        //2.如果小于20条都返回
        if (storePoints.size() <= RANGE) {
            return storePoints;
        } else {
            //3.如果多于20条则将距离最近的20条数据返回
            Map<Double, StorePoints> map = new TreeMap<>();
            for (StorePoints s :
                    storePoints) {
                PointEntity pointA = PointEntity.builder().x(s.getLongitude()).y(s.getLatitude()).build();
                double distance = CoordinateUtil.distanceToPoint(pointA, pointEntity);
                map.put(distance, s);
            }
            Map<Double, StorePoints> resMap = new TreeMap<>();
            for (Map.Entry<Double, StorePoints> entry : map.entrySet()) {
                resMap.put(entry.getKey(), entry.getValue());
                //只存20条
                if (resMap.size() >= RANGE) break;
            }
            //map转换为list
            List<StorePoints> storePointsList = resMap.values().stream()
                    .collect(Collectors.toList());
            return storePointsList;
        }

    }


}
