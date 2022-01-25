package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.mapper.DeliverTaskMapper;
import com.xkyz.xinke.mapper.UserOrderMapper;
import com.xkyz.xinke.model.DeliverTask;
import com.xkyz.xinke.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DeliverTaskService {

    @Autowired
    DeliverTaskMapper deliverTaskMapper;

    public List<DeliverTask> getDeliverTaskList(Integer status) {
        Example example = new Example(DeliverTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", status);
        return deliverTaskMapper.selectByExample(example);
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
        return deliverTaskMapper.updateByPrimaryKeySelective(res);//TODO 状态更新不成功
    }

}
