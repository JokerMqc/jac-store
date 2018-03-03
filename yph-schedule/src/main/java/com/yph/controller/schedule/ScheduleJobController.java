package com.yph.controller.schedule;

import com.github.pagehelper.PageInfo;
import com.yph.common.annotation.SysLog;
import com.yph.common.result.CommonResult;
import com.yph.entity.schedule.ScheduleJob;
import com.yph.service.schedule.IScheduleJobService;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : Administrator Hzhan
 * @create ：2018/1/22
 **/
@RestController
@RequestMapping("sys/schedule")
public class ScheduleJobController {

    @Autowired
    private IScheduleJobService scheduleJobService;

    /**
     * 定时任务列表  分页查询
     */
    @RequestMapping("/findScheduleJobByPage")
    public CommonResult findScheduleJobByPage(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = scheduleJobService.findScheduleJobByPage(params, pageNum, pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }


    /**
     * 定时任务信息  通过Id 查询信息
     */
    @RequestMapping("/info/{id}")
    public CommonResult findScheduleJobById(@PathVariable("id") Long id) {
        ScheduleJob scheduleJob = scheduleJobService.findScheduleJobById(id);
        return CommonResult.SUCCESS(scheduleJob);
    }

    /**
     * 保存定时任务
     */
    @SysLog("保存定时任务")
    @RequestMapping("/saveScheduleJob")
    public CommonResult saveScheduleJob(ScheduleJob scheduleJob) {
        scheduleJobService.save(scheduleJob);
        return CommonResult.SUCCESS();
    }

    /**
     * 修改定时任务
     */
    @SysLog("修改定时任务")
    @RequestMapping("/updateScheduleJob")
    public CommonResult updateScheduleJob(ScheduleJob scheduleJob) {
        int result = scheduleJobService.update(scheduleJob);
        if (result < 1) {
            return CommonResult.ERROR("修改定时任务失败");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 删除定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("delScheduleJobById")
    public CommonResult delScheduleJobById(Long id) {
      int result  =  scheduleJobService.delScheduleJobById(id);
      if(result<1){
          return CommonResult.ERROR();
      }
        return CommonResult.SUCCESS();
    }

    /**
     * 运行定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("runScheduleJobById")
    public CommonResult runScheduleJobById(Long id) {
        scheduleJobService.runById(id);
        return CommonResult.SUCCESS();
    }

    /**
     * 暂停定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("pauseScheduleJobById")
    public CommonResult pauseScheduleJobById(Long id) {
        scheduleJobService.pauseById(id);
        return CommonResult.SUCCESS();
    }


    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/resumeScheduleJobById")
    public CommonResult resumeScheduleJobById(Long id) {
        scheduleJobService.resumeById(id);
        return CommonResult.SUCCESS();
    }


    /***********************************     批量操作         ******************************************/
    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @RequestMapping("/batchDelScheduleJob")
    public CommonResult batchDelScheduleJob(@RequestParam Long[] jobIds) {
        int result = scheduleJobService.batchDelScheduleJob(jobIds);

        if (result < 1) {
            return CommonResult.ERROR("删除定时任务失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @RequestMapping("/batchRun")
    public CommonResult batchRun(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return CommonResult.SUCCESS();
    }

    /**
     * 暂停定时任务
     */
    @SysLog("暂停定时任务")
    @RequestMapping("/batchPause")
    public CommonResult batchPause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return CommonResult.SUCCESS();
    }

    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/batchResume")
    public CommonResult batchResume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return CommonResult.SUCCESS();
    }


}
