package com.xkyz.xinke.common;

import com.xkyz.xinke.pojo.UserOrderView;

import java.util.Comparator;

public class UserOrderViewComparator implements Comparator<UserOrderView> {
    @Override
    public int compare(UserOrderView o1, UserOrderView o2) {
        if (o1.getUserOrder().getOrderUpdateTime() > o2.getUserOrder().getOrderUpdateTime()) {
            return -1;
        } else {
            return 1;
        }
    }
}