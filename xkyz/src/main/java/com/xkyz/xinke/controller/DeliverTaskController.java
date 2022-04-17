package com.xkyz.xinke.controller;

import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.pojo.DeliverTaskView;
import com.xkyz.xinke.service.DeliverTaskService;
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

@Api(tags = "揽收员任务API")
@RestController()
@RequestMapping("/sys/deliver")
public class DeliverTaskController {
    @Autowired
    private DeliverTaskService deliverTaskService;

    @ApiOperation("获取揽收员任务详情")
    @PostMapping(value = "/get")
    public ResponseEntity<DeliverTask> getDeliverTask(@ApiParam("揽收员任务ID") Integer taskId) {
        DeliverTask deliverTask = deliverTaskService.getDeliverTaskByTaskId(taskId);
        return ResponseEntity.ok(deliverTask);

    }

    @ApiOperation("获取新任务列表")
    @PostMapping(value = "/list")
    public ResponseEntity<List<DeliverTaskView>> getDeliverTaskList() {
        List<DeliverTaskView> list = deliverTaskService.getDeliverTaskList();
        return ResponseEntity.ok(list);
    }

    @ApiOperation("更新揽收员任务状态")
    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> updateUserOrder(
            @ApiParam("揽收员任务Id") Integer taskId, @ApiParam("揽收员任务状态") Integer status) {
        deliverTaskService.updateDeliverTask(taskId, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
