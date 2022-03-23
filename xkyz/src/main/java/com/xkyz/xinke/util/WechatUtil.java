package com.xkyz.xinke.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatUtil
 * @Version 1.0
 */
public class WechatUtil {
    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        return accessWechatAPI(code, requestUrl);
    }

    public static JSONObject accessWechatAPI(String code, String requestUrl) {
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", "wx79444d769f2eeabd");
        //小程序secret
        requestUrlParam.put("secret", "ed2d7b0b4ed7719a96f03d3abd2c7d85");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }


    public static JSONObject getUserInfoWithinPhone(String code) {
//        JSONObject sessionKeyOrOpenId = getSessionKeyOrOpenId(userToken);
//        String sessionKey = sessionKeyOrOpenId.getString("session_key");
//        JSONObject userInfo = getUserInfo(encryptedData, sessionKey, iv);
        String getRequestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx79444d769f2eeabd&secret=ed2d7b0b4ed7719a96f03d3abd2c7d85";
        JSONObject getJson = JSON.parseObject(HttpClientUtil.doGet(getRequestUrl));
        String accessToken = getJson.getString("access_token");
        String postRequestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken + "?code=" + code+"?debug=1";
//        String postRequestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序端返回的code
//        requestUrlParam.put("js_code", code);
//        requestUrlParam.put("access_token", accessToken);
        //TODO invalid accessToken 原因未知,待后续查证
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(postRequestUrl));
        return jsonObject;
    }

//    POST https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN



    public static String parseUserInfo(String encryptedData, String code, String iv) {
//        JSONObject user = getSessionKeyOrOpenId(code);
//        if(user==null) throw new EmException(ExceptionEnums.INVALID_USER_CODE);
//        String sessionKey = user.getString("session_key");
        String sessionKey = "8nMZzJrdWJ3k4Ap5ub43bw==";
        JSONObject userInfo = getUserInfo(encryptedData, sessionKey, iv);
//        JSONObject userInfo = decryptData(encryptedData, sessionKey, iv);

        String phone_info = userInfo.getString("phone_info");
        return phone_info;

    }


//    SessionKey sessionKey = sessionKeyDao.createLambdaQuery().andEq(SessionKey::getUserId, userId).single();
//            if (sessionKey == null) {
//        return GlobalReponse.fail("获取信息出错");
//    }
//    AlgorithmParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(encrypDataDTO.getIv()));
//    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//    SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(sessionKey.getSessionKey()), "AES");
//            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
//    String result = new String(cipher.doFinal(Base64.decodeBase64(encrypDataDTO.getEncrypdata())), StandardCharsets.UTF_8);
//    JSONObject jsonObject = JSON.parseObject(result);
//    String phone = (String) jsonObject.get("phoneNumber");
//    //查询该手机号是否存在站长里，如果存在，则绑定上站长，以小程序的user为准，同步后台新建的站长信息，
//    // 站长详情表里的user_id管理也同步成小程序的，删除后台的站长user表
//
//    //拿手机号去站长详情里查询是否存在该手机号
//    Stationmaster sm = stationmasterDao.createLambdaQue


    public static byte[] completToBase(byte[] bytes, int base) {
        byte[] temp = new byte[]{};
        if (bytes.length % base != 0) {
            int groups = bytes.length / base + (bytes.length % base != 0 ? 1 : 0);
            temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(bytes, 0, temp, 0, bytes.length);
        }
        return temp;
    }

    public static JSONObject decryptData(String encryptedData, String iv, String sessionKey)  {
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
//        completToBase
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            keyByte = completToBase(keyByte, base);
            ivByte = completToBase(ivByte, base);
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
//        Byte keyByte = new Byte(sessionKey);
//        byte[] keyByte = CodecSupport.toBytes(sessionKey);

        // 偏移量
        byte[] ivByte = Base64.decode(iv);
//        Byte ivByte = new Byte(iv);
//        byte[] ivByte = CodecSupport.toBytes(iv);


        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
//            int base = 16;
//            keyByte = completToBase(keyByte, base);
//            ivByte = completToBase(ivByte, base);
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
