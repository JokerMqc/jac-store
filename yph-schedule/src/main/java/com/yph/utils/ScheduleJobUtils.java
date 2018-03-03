package com.yph.utils;

import com.yph.common.utils.SpringContextUtils;
import com.yph.entity.schedule.ScheduleJob;
import com.yph.entity.schedule.ScheduleJobLog;
import com.yph.service.schedule.IScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


/**
 * 定时任务
 */
@Slf4j
public class ScheduleJobUtils extends QuartzJobBean {
	
	private final ExecutorService executorService  = (ExecutorService) SpringContextUtils.getBean("executorService");

	/**
	 *  执行
	 * @param context
	 * @throws JobExecutionException
	 */
    @Override
    protected void executeInternal(JobExecutionContext context){

		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
        		.get(ScheduleJob.JOB_PARAM_KEY);

        //获取记录日志的 bean
        IScheduleJobLogService scheduleJobLogService = (IScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogServiceImpl");
        
        //数据库保存执行记录
        ScheduleJobLog scheduleJobLog = new ScheduleJobLog();
		scheduleJobLog.setJobId(scheduleJob.getId());
		scheduleJobLog.setBeanName(scheduleJob.getBeanName());
		scheduleJobLog.setMethodName(scheduleJob.getMethodName());
		scheduleJobLog.setParams(scheduleJob.getParams());
		scheduleJobLog.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
			log.info("任务准备执行，任务ID：{}" , scheduleJob.getId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), 
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = executorService.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			scheduleJobLog.setTimes((int)times);
			//任务状态    0：成功    1：失败
			scheduleJobLog.setStatus(0);
			
			log.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			log.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			scheduleJobLog.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			scheduleJobLog.setStatus(1);
			scheduleJobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.saveScheduleJobLog(scheduleJobLog);
		}
    }
}
