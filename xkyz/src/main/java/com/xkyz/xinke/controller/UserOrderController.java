package com.xkyz.xinke.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.toolkit.url.UrlUtil;
import com.xkyz.xinke.model.User;
import com.xkyz.xinke.model.UserOrder;
import com.xkyz.xinke.pojo.IncomeView;
import com.xkyz.xinke.pojo.ReturnMSG;
import com.xkyz.xinke.pojo.UserOrderView;
import com.xkyz.xinke.pojo.UserOrderWithCompanyView;
import com.xkyz.xinke.service.UserOrderService;
import com.xkyz.xinke.service.UserService;
import com.xkyz.xinke.service.WxService;
import com.xkyz.xinke.util.HttpClientUtil;
import com.xkyz.xinke.util.WXConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.util.URLUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "用户订单API")
@RestController()
@RequestMapping("/sys/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private WxService wxService;

    @ApiOperation("创建订单")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/create")
    public ResponseEntity<ReturnMSG> addUserOrder(UserOrder userOrder) {
        int i = userOrderService.addUserOrder(userOrder);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));
    }

    @ApiOperation("取消订单")
    @PostMapping(value = "/cancel")
    public ResponseEntity<ReturnMSG> cancelUserOrder(@ApiParam("orderNo")String orderNo) {
        int i=userOrderService.cancelUserOrder(orderNo);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));
    }

    @ApiOperation("获取订单信息")//作用在API方法上，对操作进行说明
    @GetMapping(value = "/get")
    public ResponseEntity<UserOrderView> getUserOrderByOrderNo(String orderNo) {
        UserOrderView userOrderView = userOrderService.getUserOrderByOrderNo(orderNo);
        return ResponseEntity.ok(userOrderView);
    }

    @ApiOperation("根据userToken和状态获取订单列表")//作用在API方法上，对操作进行说明
    @PostMapping(value = "/list")
    public ResponseEntity<List<UserOrderView>> getUserOrderListByOpenId(@ApiParam("token")String token, @ApiParam("订单状态：1未结算，2已取消，3已发货")Integer status) {
        List<UserOrderView> list = userOrderService.getUserOrderListByOpenId(token, status);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("根据deliverToken和揽收状态获取订单列表")
    @PostMapping(value = "/deliverOrderList")
    public ResponseEntity<List<UserOrderWithCompanyView>> getUserOrderListByDeliverToken(@ApiParam("deliverToken")String deliverToken, @ApiParam("揽收状态：1.新任务，2未揽收，3已揽收")Integer deliverStatus) {
        List<UserOrderWithCompanyView> list=userOrderService.getUserOrderListByDeliverToken(deliverToken,deliverStatus);
        return ResponseEntity.ok(list);
    }
    @ApiOperation("根据网点id获取订单列表")
    @PostMapping(value = "/getListByPointsId")
    public ResponseEntity<List<UserOrderWithCompanyView>> getListByPointsId(@ApiParam("pointsId")Integer pointsId) {
        List<UserOrderWithCompanyView> list=userOrderService.getListByPointsId(pointsId);
        return ResponseEntity.ok(list);
    }

    @ApiOperation("更新订单信息")
    @GetMapping(value = "/update")
    public ResponseEntity<ReturnMSG> updateUserOrder(
            @ApiParam("订单编号") String orderNo, @ApiParam("订单所需变更的状态:1未结算，2已取消，3已发货") Integer status) {
        int i = userOrderService.updateUserOrder(orderNo, status);
        return ResponseEntity.ok().body(new ReturnMSG("ok"));

    }

    @ApiOperation("商家今日收益")
    @PostMapping(value = "/storeTodayIncome")
    public ResponseEntity<IncomeView> storeTodayIncome(
            @ApiParam("网点ID") Integer pointsId) {
        IncomeView incomeAndCount = userOrderService.getIncomeAndCount(pointsId);
        return ResponseEntity.ok(incomeAndCount);
    }

//    public static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";
//    @GetMapping(value = "getOpenId")
//    public String getOpenId(@ApiParam(value = "code")String code){
//        String url = URL_ACCESS_TOKEN.replace("APPID", WeChatConfig.APP_ID).replace("SECRET", WeChatConfig.SECRET_ID).replace("CODE", code);
//        JSONObject jsonObject =UrlUtil.httpsRequest(url,"GET",null);
//        String openid = jsonObject.getString("openid");
//        return openid;
//    }

    @PostMapping("/notify")
    @ApiOperation("微信支付通知")
    public String wxPayNotify(HttpServletRequest request) {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxService.payBack(resXml);
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }

//    @PostMapping("/preparePay")
//    @ApiOperation("微信支付预备")
//    public String wxPayPrepare() throws Exception {
////        Map map = wxService.doUnifiedOrder();
//        //TODO
//        return null;
//    }
//
//    @GetMapping("/push")
//    public String push(@ApiParam("openid")String token,@ApiParam("templateId")String templateId) {
//        User openId = userService.getOpenIdBySkey(token);
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
//                + WXConfigUtil.APP_ID
//                + "&secret="
//                + WXConfigUtil.SECRET_ID;
//        String result=HttpClientUtil.doGet(url);
////        String result = HttpUtil.sendGet(url);
//        JSONObject object = JSON.parseObject(result);
//        String Access_Token = object.getString("access_token");
//        Template template = new Template();
//
//        template.setTemplate_id(WXConfigUtil.TEMPLATE_ID);
//        template.setTouser(openid);
//        template.setPage("pages/index/index");
//        List<TemplateParam> paras = new ArrayList<>();
//        paras.add(new TemplateParam("time1", "2019-12-28 10:00:00"));
//        paras.add(new TemplateParam("thing2", "监控1发现2人"));
//        template.setTemplateParamList(paras);
//        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
//        requestUrl = requestUrl.replace("ACCESS_TOKEN", Access_Token);
//
//        System.out.println(template.toJSON());
//        net.sf.json.JSONObject jsonResult = UrlUtil.httpsRequest(requestUrl, "POST", template.toJSON());
//        if (jsonResult != null) {
//            System.out.println(jsonResult);
//            int errorCode = jsonResult.getInt("errcode");
//            String errorMessage = jsonResult.getString("errmsg");
//            if (errorCode == 0) {
//                System.out.println("Send Success");
//            } else {
//                System.out.println("订阅消息发送失败:" + errorCode + "," + errorMessage);
//            }
//        }
//        return null;
//    }

}
