package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.ExpressCompanyMapper;
import com.xkyz.xinke.mapper.UserAddressMapper;
import com.xkyz.xinke.mapper.UserOrderMapper;
import com.xkyz.xinke.model.ExpressCompany;
import com.xkyz.xinke.model.UserAddress;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.IncomeView;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.pojo.UserOrderWithCompanyView;
import com.xkyz.xinke.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserOrderService {

    @Autowired
    UserOrderMapper userOrderMapper;
    @Autowired
    UserAddressMapper userAddressMapper;
    @Autowired
    ExpressCompanyMapper expressCompanyMapper;

    public UserOrderView getUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = UserOrder.builder().orderNo(orderNo).build();
        UserOrder order = userOrderMapper.selectOne(userOrder);
        UserAddress sendAddress = userAddressMapper.selectByPrimaryKey(order.getSendAddress());
        UserAddress receiveAddress = userAddressMapper.selectByPrimaryKey(order.getReceiveAddress());
        UserOrderView userOrderView = UserOrderView.builder().sendAddress(sendAddress).receiveAddress(receiveAddress).userOrder(order).build();
        return userOrderView;
    }


    public int addUserOrder(UserOrder userOrder) {
        //订单创建状态给默认值:status--1未结算 deliverStatus--1.新任务
        //TODO 实际价格 = 首重价格 + (总重量 - 1) * 续重价格
        Long current = System.currentTimeMillis() / 1000;
        userOrder.setOrderTime(current);
        userOrder.setOrderUpdateTime(current);
        userOrder.setOrderNo(UUID.randomUUID().toString().substring(0,32));
        userOrder.setStatus(1);
        userOrder.setDeliverStatus(1);
        return userOrderMapper.insert(userOrder);
    }

    public int deleteUserOrderByOrderNo(String orderNo) {
        UserOrder userOrder = UserOrder.builder().orderNo(orderNo).build();
        return userOrderMapper.delete(userOrder);
    }


    public int cancelUserOrder(String orderNo) {
        UserOrder userOrder = getUserOrder(orderNo);
        Example example = new Example(UserOrder.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo", userOrder.getOrderNo());
        userOrder.setStatus(3);
        userOrder.setDeliverStatus(2);
        userOrder.setOrderUpdateTime(System.currentTimeMillis() / 1000);
        return userOrderMapper.updateByExampleSelective(userOrder, example);
    }

    public int finishOrder(String orderNo) {
        UserOrder userOrder = getUserOrder(orderNo);
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo", userOrder.getOrderNo());
        userOrder.setStatus(3);
//        userOrder.setDeliverStatus(2);//TODO 待确认揽收员状态 是否变更
        userOrder.setOrderUpdateTime(System.currentTimeMillis() / 1000);
        return userOrderMapper.updateByExampleSelective(userOrder, example);
    }

    private UserOrder getUserOrder(String orderNo) {
        UserOrderView userOrderView = getUserOrderByOrderNo(orderNo);
        UserOrder userOrder = userOrderView.getUserOrder();
        if (userOrder == null) {
            throw new EmException(ExceptionEnums.USER_ORDER_NOT_EXIST);
        }
        return userOrder;
    }

    public int updateUserOrder(UserOrder updateUser) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo", updateUser.getOrderNo());
//        userOrder.setStatus(updateUser.getStatus());
//        userOrder.setPrice(updateUser.getPrice());
//        userOrder.setExpressCompanyId(updateUser.getExpressCompanyId());
//        userOrder.setStuffType(updateUser.getStuffType());
//        userOrder.setStuffType(updateUser.getStuffType());
        if (this.getUserOrderByOrderNo(updateUser.getOrderNo())==null) throw new EmException(ExceptionEnums.USER_ORDER_NOT_EXIST);
        updateUser.setDeliverStatus(2);
        updateUser.setStatus(1);
        updateUser.setOrderUpdateTime(System.currentTimeMillis() / 1000);
        if (StringUtils.isEmpty(updateUser.getImageUrl())) throw new EmException(ExceptionEnums.IMAGE_URL_NOT_EXIST);
        //更新数据 订单更新时间 方便后期商家查询当日收益
        updateUser.setOrderUpdateTime(System.currentTimeMillis() / 1000);
        return userOrderMapper.updateByExampleSelective(updateUser, example);
    }

    public List<UserOrderView> getUserOrderListByOpenId(String token, Integer status) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userToken", token);
        criteria.andEqualTo("status", status);
        List<UserOrder> list = userOrderMapper.selectByExample(example);
        List<UserOrderView> resList = new ArrayList<>();
        list.stream().forEach(s -> {
            UserAddress sendAddress = userAddressMapper.selectByPrimaryKey(s.getSendAddress());
            UserAddress receiveAddress = userAddressMapper.selectByPrimaryKey(s.getReceiveAddress());
            UserOrderView userOrderView = UserOrderView.builder().userOrder(s).sendAddress(sendAddress).receiveAddress(receiveAddress).build();
            resList.add(userOrderView);
        });
        return resList;
    }

    public List<UserOrderWithCompanyView> getUserOrderListByDeliverToken(String deliverToken, Integer deliverStatus) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(deliverToken)){
            criteria.andEqualTo("deliverToken", deliverToken);
        }
        criteria.andEqualTo("deliverStatus", deliverStatus);
        List<UserOrder> list = userOrderMapper.selectByExample(example);
        return getUserOrderWithCompanyViews(list);
    }

    public List<UserOrderWithCompanyView> getNewList( Integer deliverStatus, Integer pointsId) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("deliverToken", deliverToken);
        criteria.andEqualTo("deliverStatus", deliverStatus);
        criteria.andEqualTo("pointsId", pointsId);
        List<UserOrder> list = userOrderMapper.selectByExample(example);
        return getUserOrderWithCompanyViews(list);
    }

    public List<UserOrderWithCompanyView> getListByPointsId(Integer pointsId) {
        Example example = new Example(UserOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pointsId", pointsId);
        List<UserOrder> list = userOrderMapper.selectByExample(example);
        return getUserOrderWithCompanyViews(list);
    }

    private List<UserOrderWithCompanyView> getUserOrderWithCompanyViews(List<UserOrder> list) {
        List<UserOrderWithCompanyView> resList = new ArrayList<>();
        list.stream().forEach(s -> {
            UserAddress sendAddress = userAddressMapper.selectByPrimaryKey(s.getSendAddress());
            UserAddress receiveAddress = userAddressMapper.selectByPrimaryKey(s.getReceiveAddress());
            ExpressCompany expressCompany = expressCompanyMapper.selectByPrimaryKey(s.getExpressCompanyId());
            UserOrderWithCompanyView userOrderWithCompanyView = UserOrderWithCompanyView.builder().sendAddress(sendAddress).receiveAddress(receiveAddress).userOrder(s).expressCompany(expressCompany).build();
            resList.add(userOrderWithCompanyView);
        });
        return resList;
    }


    public IncomeView getIncomeAndCount(Integer pointsId) {
        Long theBeginOfToday = TimeUtil.getTodayStartTime();
        Double storeTodayIncome = userOrderMapper.getStoreTodayIncome(pointsId, theBeginOfToday);
        Double storeAllIncome = userOrderMapper.getStoreAllIncome(pointsId);
        Double count = userOrderMapper.getStoreTodayCount(pointsId, theBeginOfToday);
        IncomeView incomeView = IncomeView.builder().incomeOfAll(storeAllIncome).incomeOfToday(storeTodayIncome).count(count).build();
        return incomeView;
    }


}
