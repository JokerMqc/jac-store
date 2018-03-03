package com.yph.service.schedule;

import com.github.pagehelper.PageInfo;
import com.yph.entity.schedule.ScheduleJob;
import io.swagger.models.auth.In;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface IScheduleJobService {

    /**
     * 根据ID，查询定时任务
     */
    ScheduleJob findScheduleJobById(Long jobId);

    /**
     * 查询定时任务列表
     */
    List<ScheduleJob> findListScheduleJob(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务
     */
    void save(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     */
    int update(ScheduleJob scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 批量 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 批量 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 批量 恢复运行
     */
    void resume(Long[] jobIds);



    /**
     * 立即执行
     */
    void runById(Long id);

    /**
     * 暂停运行
     */
    void pauseById(Long id);

    /**
     * 恢复运行
     */
    void resumeById(Long id);



    /**
     *  分页查询
     * @param params
     * @return
     */
    PageInfo findScheduleJobByPage(Map<String, Object> params, Integer pageNum,Integer pageSize);

    /**
     *  批量删除
     * @param jobIds
     * @return
     */
    int batchDelScheduleJob(Long[] jobIds);


    /**
     *  通过Id 删除
     * @param id
     * @return
     */
    int delScheduleJobById(Long id);

    /**
     *  测试使用
     * @param params
     */
    void testTask(String params);





}
