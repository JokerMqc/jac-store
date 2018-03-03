package com.yph.service.schedule;

import com.github.pagehelper.PageInfo;
import com.yph.entity.schedule.ScheduleJobLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IScheduleJobLogService {

    /**
     * 根据ID，查询定时任务日志
     */
    ScheduleJobLog findScheduleJobLog(Long jobId);

    /**
     * 查询定时任务日志列表
     */
    List<ScheduleJobLog> findListScheduleJobLog(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务日志
     */
    void saveScheduleJobLog(ScheduleJobLog scheduleJobLog);

    /**
     *  分页查询
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findScheduleJobLogByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     *  批量删除日志
     * @param ids
     * @return
     */
    int batchDelScheduleJobLog(Long[] ids);

    /**
     *  删除日志
     * @param id
     * @return
     */
    int delScheduleJobLog(Long id);

    /**
     *  定时任务 ----> 删除3天前的日志
     * @return
     */
    int delBefore3DayTask();



}
