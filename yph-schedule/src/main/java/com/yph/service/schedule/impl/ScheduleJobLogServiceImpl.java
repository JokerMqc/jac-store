package com.yph.service.schedule.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.entity.schedule.ScheduleJobLog;
import com.yph.mapper.schedule.ScheduleJobLogMapper;
import com.yph.service.schedule.IScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Administrator Hzhan
 * @create ：2018/1/22
 **/
@Slf4j
@Service
@Transactional
public class ScheduleJobLogServiceImpl implements IScheduleJobLogService {

    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    /**
     *  查询 通过Id
     * @param id
     * @return
     */
    @Override
    public ScheduleJobLog findScheduleJobLog(Long id) {
        return scheduleJobLogMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<ScheduleJobLog> findListScheduleJobLog(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void saveScheduleJobLog(ScheduleJobLog scheduleJobLog) {
        scheduleJobLogMapper.insert(scheduleJobLog);
    }

    /**
     * 分页查询
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findScheduleJobLogByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(" create_time desc");
        List<ScheduleJobLog> list = scheduleJobLogMapper.findScheduleJobLogByPage(params);
        return new PageInfo<>(list);
    }

    @Override
    public int batchDelScheduleJobLog(Long[] ids) {
        return scheduleJobLogMapper.batchDelScheduleJobLog(ids);
    }

    @Override
    public int delScheduleJobLog(Long id) {
        return scheduleJobLogMapper.delScheduleJobLogById(id);
    }

    /**
     * 删除3天前的日志
     *
     * @return
     */
    @Override
    public int delBefore3DayTask() {
        Date now = new Date();
        HashMap<String, Object> params = new HashMap<>();
        params.put("nowDate", now);
        return scheduleJobLogMapper.delBefore3Day(params);
    }


}
