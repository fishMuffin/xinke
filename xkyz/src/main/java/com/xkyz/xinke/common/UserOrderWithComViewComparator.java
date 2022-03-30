package com.xkyz.xinke.common;

import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.pojo.UserOrderWithCompanyView;

import java.util.Comparator;

public class UserOrderWithComViewComparator implements Comparator<UserOrderWithCompanyView> {
    @Override
    public int compare(UserOrderWithCompanyView o1, UserOrderWithCompanyView o2) {
        if (o1.getUserOrder().getOrderUpdateTime() > o2.getUserOrder().getOrderUpdateTime()) {
            return -1;
        } else {
            return 1;
        }
    }
}