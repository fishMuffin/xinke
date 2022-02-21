package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.DeliverTaskMapper;
import com.xkyz.xinke.mapper.StorePointsMapper;
import com.xkyz.xinke.mapper.UserOrderMapper;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.StorePoints;
import com.xkyz.xinke.pojo.DeliverTaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliverTaskService {

    @Autowired
    DeliverTaskMapper deliverTaskMapper;
    @Autowired
    StorePointsMapper storePointsMapper;
    @Autowired
    UserOrderMapper userOrderMapper;

    public List<DeliverTaskView> getDeliverTaskList(String token) {
        Example example = new Example(DeliverTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deliverToken", token);
        criteria.andEqualTo("status", 1);
        List<DeliverTask> deliverTasks = deliverTaskMapper.selectByExample(example);
        List<DeliverTaskView> resList = new ArrayList<>();
        deliverTasks.stream().forEach(s -> {
            StorePoints storePoints = storePointsMapper.selectByPrimaryKey(s.getPointsId());
            //这里从数据里重新查询，不使用DeliverTask表中的数据，不准确
            Double expressAmount =  userOrderMapper.getCountByStatus(1);
            resList.add(DeliverTaskView.builder().deliverToken(s.getDeliverToken()).ownerToken(s.getOwnerToken()).taskId(s.getTaskId()).storePoints(storePoints).expressAmount(expressAmount).status(s.getStatus()).build());
        });
        return resList;
    }


    public int addDeliverTask(DeliverTask deliverTask) {
        return deliverTaskMapper.insert(deliverTask);
    }

    public DeliverTask getDeliverTaskByTaskId(Integer taskId) {
        DeliverTask deliverTask = DeliverTask.builder().taskId(taskId).build();
        return deliverTaskMapper.selectOne(deliverTask);
    }

    public int deleteDeliverTask(Integer taskId) {
        DeliverTask build = DeliverTask.builder().taskId(taskId).build();
        return deliverTaskMapper.delete(build);
    }

    public int updateDeliverTask(Integer taskId, Integer status) {
        DeliverTask res = this.getDeliverTaskByTaskId(taskId);
        if (res == null) {
            throw new EmException(ExceptionEnums.DELIVER_TASK_NOT_EXIST);
        }
        res.setStatus(status);
        Example example = new Example(DeliverTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("taskId", res.getTaskId());
        return deliverTaskMapper.updateByExampleSelective(res, example);
    }

    //TODO 更新重量和价格 + 图片上传

}
