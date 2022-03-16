package com.xkyz.xinke;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.xkyz.xinke.pojo.PointEntity;
import com.xkyz.xinke.util.CoordinateUtil;
import com.xkyz.xinke.util.MoneyUtil;
import com.xkyz.xinke.util.TimeUtil;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class XYTest {

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


    private String getCurrentDateFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    @Test
    public void testJJ() {
        System.out.println(getCurrentDateFormat());
//        Long current = System.currentTimeMillis() / 1000;
//        System.out.println(System.currentTimeMillis() / 1000);
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(UUID.randomUUID().toString() + "-" + current);
//        System.out.println(TimeUtil.getTodayStartTime());
//        System.out.println(dataConvert("上海市"));
//        System.out.println(dataConvert("湖北省"));
//        System.out.println(dataConvert("重庆"));
    }




    @Test
    public void testXY() {
//        32.876791
//        115.790469
//        new
//        PointEntity pointA = new PointEntity(32.876791, 115.790469);
//        PointEntity pointB = new PointEntity(31.876791, 111.790469);
//        double v = CoordinateUtil.distanceToPoint(pointA, pointB);
//        System.out.println(v);

        System.out.println(UUID.randomUUID().toString().length());
        System.out.println(UUID.randomUUID().toString().substring(0,32).length());
        System.out.println(

        );

    }

    @Test
    public void testReduce(){
        BigDecimal bigDecimal = new BigDecimal(33.25);
        BigDecimal bigDecimal2 = new BigDecimal(22.145);
        BigDecimal res = MoneyUtil.moneySub(bigDecimal, bigDecimal2);
        String s = MoneyUtil.formatMoney(res + "");
        String s1 = MoneyUtil.formatMoneyToTow(res);
        System.out.println(s);
        System.out.println(s1);
    }



    @Test
    public void testSortedTree() {
        Map<Double, String> map = new TreeMap<>();
        map.put(22.33, "sdas");
        map.put(55.33, "sdas");
        map.put(1.33, "sdas");
        map.put(0.5, "sdas");
        for (Map.Entry<Double, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }

    @Test
    public void test1() {
        double distanceMeter = getDistanceMeter(32.876791, 115.790469, 31.876791, 111.790469);
        System.out.println(distanceMeter);

    }
//    **
//
//            * 根据坐标算距离
//
//* @param startLng
//
//* @param startLat
//
//* @param endLng
//
//* @param endLat
//
//* @return
//
//        */

    public static double getDistanceMeter(double startLng, double startLat, double endLng, double endLat) {

        GlobalCoordinates startGlobalCoordinates = new GlobalCoordinates(startLat, startLng);

        GlobalCoordinates endGlobalCoordinates = new GlobalCoordinates(endLat, endLng);

        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.WGS84, startGlobalCoordinates, endGlobalCoordinates);

        return geoCurve.getEllipsoidalDistance();

    }

}
