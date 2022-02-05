//package com.xkyz.xinke;
//
//import com.vividsolutions.jts.geom.Coordinate;
//import com.vividsolutions.jts.geom.GeometryFactory;
//import com.vividsolutions.jts.geom.Point;
//import com.xkyz.xinke.pojo.PointEntity;
//import com.xkyz.xinke.util.CoordinateUtil;
//import org.gavaghan.geodesy.Ellipsoid;
//import org.gavaghan.geodesy.GeodeticCalculator;
//import org.gavaghan.geodesy.GeodeticCurve;
//import org.gavaghan.geodesy.GlobalCoordinates;
//import org.junit.jupiter.api.Test;
//
//import java.util.Map;
//import java.util.TreeMap;
//
//public class XYTest {
//
//
//    @Test
//    public void testXY(){
////        32.876791
////        115.790469
////        new
//        PointEntity pointA = new PointEntity(32.876791, 115.790469);
//        PointEntity pointB = new PointEntity(31.876791, 111.790469);
//        double v = CoordinateUtil.distanceToPoint(pointA, pointB);
//        System.out.println(v);
//    }
//
//    @Test
//    public void testSortedTree(){
//        Map<Double,String> map=new TreeMap<>();
//        map.put(22.33,"sdas");
//        map.put(55.33,"sdas");
//        map.put(1.33,"sdas");
//        map.put(0.5,"sdas");
//        for (Map.Entry<Double, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey()+"---"+entry.getValue());
//        }
//    }
//
//    @Test
//    public void test1(){
//        double distanceMeter = getDistanceMeter(32.876791, 115.790469, 31.876791, 111.790469);
//        System.out.println(distanceMeter);
//
//    }
////    **
////
////            * 根据坐标算距离
////
////* @param startLng
////
////* @param startLat
////
////* @param endLng
////
////* @param endLat
////
////* @return
////
////        */
//
//    public static double getDistanceMeter(double startLng,double startLat,double endLng,double endLat){
//
//        GlobalCoordinates startGlobalCoordinates = new GlobalCoordinates(startLat, startLng);
//
//        GlobalCoordinates endGlobalCoordinates = new GlobalCoordinates(endLat, endLng);
//
//        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.WGS84, startGlobalCoordinates, endGlobalCoordinates);
//
//        return geoCurve.getEllipsoidalDistance();
//
//    }
//
//}
