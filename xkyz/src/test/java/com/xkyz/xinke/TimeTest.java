package com.xkyz.xinke;

import com.xkyz.xinke.util.TimeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(new Date());

//        System.out.println(dateString);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println(new Date().getTime());
//        Calendar instance = Calendar.getInstance();
//        instance.;

        System.out.println(TimeUtil.getTodayStartTime()/1000);
    }
}
