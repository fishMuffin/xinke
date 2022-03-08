package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.pojo.TemplateData;
import com.xkyz.xinke.pojo.WxMssVo;
import com.xkyz.xinke.service.UserService;
import com.xkyz.xinke.util.WXConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送小程序订阅消息
 */
@Api(tags = "微信通知订阅")
@RestController()
@RequestMapping("/sys/wxNotify")
public class SendWxMessageController {

    @Autowired
    UserService userService;

    private static final String ORDER_DELIVER_MESSAGE="3pm3PCy-t4Ao2TRoXrdfxaMNejKD2aPi7xWG83sZNxs";//订单配送通知
    private static final String TRANSFER_ACCEPTED="yPcewsYwfohO1cBHO1-xVjViUUdqXH6rTCTLSiHSjLY";//提现到账通知
    /*
     * 发送订阅消息
     * */
    @GetMapping("/testOne")
    public String pushOneUser() {
        return push("oVoNf5CkuZrvpJvBa4TlH-se6fak");
    }
    @ApiOperation("微信提现通知")
    @GetMapping("/notify")
    public String wxNotify(@ApiParam("用户token")String token) {
        String openId = userService.getOpenIdBySkey(token);
        return push(openId);
    }

    public String push(String openid) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id(TRANSFER_ACCEPTED);//订阅消息模板id
        wxMssVo.setPage("pages/index/index");//TODO 后期改成传参数

        Map<String, TemplateData> m = new HashMap<>(5);
        m.put("amount3", new TemplateData("100"));
        m.put("amount4", new TemplateData("150"));
        m.put("character_string1", new TemplateData("345645645"));
        m.put("phone_number2", new TemplateData("13665692919"));
        m.put("date8", new TemplateData(getCurrentDateFormat()));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }

    //订单配送
    public String pushOrderMessage(String openid,String pointsName,String phoneNumber,Double amount,String deliverName) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id(ORDER_DELIVER_MESSAGE);//订阅消息模板id
        wxMssVo.setPage("pages/index/index");//TODO 后期改成传参数
        Map<String, TemplateData> m = new HashMap<>(5);
        m.put("thing2", new TemplateData(pointsName));
        m.put("date3", new TemplateData(getCurrentDateFormat()));
        m.put("amount19", new TemplateData(amount+""));
        m.put("thing25", new TemplateData(deliverName));
        m.put("phone_number5", new TemplateData(phoneNumber));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }


    private String getCurrentDateFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }


    @GetMapping("/getAccessToken")
    public String getAccessToken() {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", WXConfigUtil.APP_ID);  //
        params.put("APPSECRET", WXConfigUtil.SECRET_ID);  //
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }
}