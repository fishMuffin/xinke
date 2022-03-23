package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.WechatTransfer;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.pojo.ReturnMSG;
import com.xkyz.xinke.service.DeliverTaskService;
import com.xkyz.xinke.service.WechatTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "微信提现")
@RestController()
@RequestMapping("/sys/transfer")
public class WechatTransterController {
    @Autowired
    private WechatTransferService wechatTransferService;

//    @ApiOperation("查询微信转账")
//    @PostMapping(value = "/get")
//    public ResponseEntity<WechatTransfer> getWechatTransfer(@ApiParam("openId") Integer openId) {
//        WechatTransfer deliverTaskByTaskId = wechatTransferService.getWechatTransferByOpenId(openId);
//        return ResponseEntity.ok(deliverTaskByTaskId);
//    }

    @ApiOperation("添加微信转账")
    @PostMapping(value = "/add")
    public ResponseEntity<ReturnMSG> addWechatTransfer(@ApiParam("userToken") String userToken, @ApiParam("转账金额") Double amount) {
        wechatTransferService.addDeliverTask(userToken,amount);
        return ResponseEntity.ok(new ReturnMSG("ok"));
    }

}
