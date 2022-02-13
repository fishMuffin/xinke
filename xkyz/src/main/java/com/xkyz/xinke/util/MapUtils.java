package com.xkyz.xinke.util;

import java.net.URL;

public class MapUtils {
    /**
     * 腾讯API 逆地址解析  根据经纬度得到地址信息
     * @param lat 经度
     * @param lng 纬度
     * @param key 开发密钥
     * @return
     */

    private static final String KEY="H2PBZ-7REK4-OJTUK-DDVEK-BG2LF-NYFKM";

    public static String getAddress(String lat,String lng) {
        //lat 小  log  大
        //纬度前 经度后 "https://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+lng+"&key="+key+"&get_poi=1";
        String urlString = "https://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+lng+"&key="+KEY+"&get_poi=1";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        //System.out.println(res);
        return res;
    }

    /**
     * 腾讯API 坐标转换
     * @param lat
     * @param lng
     * @param key
     * @return
     */
    public static String changeAddress(String lat,String lng,String key) {
        //lat 小  log  大
        String urlString = "https://apis.map.qq.com/ws/coord/v1/translate?locations="+lat+","+lng+"&type=1&key="+key;
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        //System.out.println(res);
        return res;
    }

}
