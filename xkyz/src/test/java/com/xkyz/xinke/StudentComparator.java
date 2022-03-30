package com.xkyz.xinke;

import com.xkyz.xinke.pojo.UserOrderView;

import java.util.Comparator;

class StudentComparator implements Comparator<UserOrderView> {

//	/**
//	  * return a negative integer, zero, or a positive integer as the first argument is less than,
//	  * 			equal to, or greater than the second.
//	  */
//	@Override
//	public int compare(Student s1, Student s2) {
//
//		if(s1.getScore()>s2.getScore()){	//greater
//			return -1;
//		}else if(s1.getScore()==s2.getScore()){	//equals
//			if(s1.getId()>s2.getId()){
//				return 1;
//			}else if(s1.getId()==s2.getId()){
//				return 0;
//			}else{
//				return -1;
//			}
//		}else{	//less
//			return 1;
//		}
//	}

    @Override
    public int compare(UserOrderView o1, UserOrderView o2) {
        if (o1.getUserOrder().getOrderUpdateTime() > o2.getUserOrder().getOrderUpdateTime()) {
            return -1;
        } else {
            return 1;
        }
    }
}