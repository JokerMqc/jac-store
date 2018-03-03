package com.yph.service.schedule.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.constant.ScheduleStatus;
import com.yph.entity.schedule.ScheduleJob;
import com.yph.mapper.schedule.ScheduleJobMapper;
import com.yph.service.schedule.IScheduleJobService;
import com.yph.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
public class ScheduleJobServiceImpl implements IScheduleJobService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        // 获取任务列表
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.findListScheduleJob(new HashMap<String, Object>());
        // 将任务列表中的任务放进定时任务中
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    /**
     *  通过Id获取任务
     * @param jobId
     * @return
     */
    @Override
    public ScheduleJob findScheduleJobById(Long jobId) {
        return scheduleJobMapper.findScheduleJobById(jobId);
    }

    /**
     *  获取任务列表
     * @param map
     * @return
     */
    @Override
    public List<ScheduleJob> findListScheduleJob(Map<String, Object> map) {
        return scheduleJobMapper.findListScheduleJob(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobMapper.queryTotal(map);
    }

    /**
     * 新建一个任务
     *
     * @param scheduleJob
     */
    @Override
    @Transactional
    public void save(ScheduleJob scheduleJob){
        scheduleJob.setFlag(new Byte("0"));
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        scheduleJobMapper.insert(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    /**
     *      更新一个任务
     * @param scheduleJob
     */
    @Override
    @Transactional
    public int update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
         return   scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
    }

    /**
     *  批量删除任务
     * @param jobIds
     */
    @Override
    @Transactional
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        scheduleJobMapper.deleteBatch(jobIds);
    }

    /**
     *  批量更新任务状态
     * @param jobIds
     * @param status
     * @return
     */
    @Override
    @Transactional
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return scheduleJobMapper.updateBatch(map);
    }

    /**
     *  运行任务
     * @param jobIds
     */
    @Override
    @Transactional
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, findScheduleJobById(jobId));
        }
    }

    /**
     *  批量暂停状态
     * @param jobIds
     */
    @Override
    @Transactional
    public void pause(Long[] jobIds) {
        ScheduleJob scheduleJob =null;
        for (Long jobId : jobIds) {
            // 更新状态
            scheduleJob = new ScheduleJob();
            scheduleJob.setId(jobId);
            scheduleJob.setStatus(1);
            scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
            // 暂停任务
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    /**
     *  批量恢复状态
     * @param jobIds
     */
    @Override
    @Transactional
    public void resume(Long[] jobIds) {
        ScheduleJob scheduleJob = null;
        for (Long jobId : jobIds) {
            scheduleJob = new ScheduleJob();
            scheduleJob.setId(jobId);
            scheduleJob.setStatus(0);
            scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
//        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

    /**
     *  任务列表 分页获取
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findScheduleJobByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        if (params.get("orderBy") != null) {
            PageHelper.orderBy(params.get("orderBy") + "  desc");
        } else {
            PageHelper.orderBy(" create_time desc");
        }
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        List<ScheduleJob> list = scheduleJobMapper.findScheduleJobByPage(params);
        return new PageInfo<>(list);
    }


    /**
     *  批量删除任务
     * @param jobIds
     * @return
     */
    @Override
    @Transactional
    public int batchDelScheduleJob(Long[] jobIds) {
        for (Long jobId : jobIds) {
            scheduleJobMapper.delScheduleJobById(jobId);
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        return 1;
    }

    /**
     * 删除定时任务
     *
     * @param id
     * @return
     */
    @Override
    public int delScheduleJobById(Long id) {
        // 将数据库列表从中状态更新标志
        scheduleJobMapper.delScheduleJobById(id);
        // 将定时任务从任务列表中删除
        ScheduleUtils.deleteScheduleJob(scheduler, id);
        return 1;
    }

    /**
     *  测试任务
     * @param params
     */
    @Override
    public void testTask(String params) {
        log.info("[ 测试任务 START  -------------------->]");

        ScheduleJob scheduleJobById = scheduleJobMapper.findScheduleJobById(4L);
        System.out.println(scheduleJobById);
        log.info("[ 测试任务 END    -------------------->]");

    }

    /**
     *  通过Id 执行任务
     * @param id
     */
    @Override
    public void runById(Long id) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setId(id);
        scheduleJob.setStatus(0);
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
        ScheduleUtils.run(scheduler, findScheduleJobById(id));
    }

    /**
     *  暂停任务
     * @param id
     */
    @Override
    public void pauseById(Long id) {

        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setId(id);
        scheduleJob.setStatus(1);
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);

        ScheduleUtils.pauseJob(scheduler, id);
    }

    /**
     *  恢复任务
     * @param id
     */
    @Override
    public void resumeById(Long id) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setId(id);
        scheduleJob.setStatus(0);
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
        ScheduleUtils.resumeJob(scheduler, id);
    }

}
