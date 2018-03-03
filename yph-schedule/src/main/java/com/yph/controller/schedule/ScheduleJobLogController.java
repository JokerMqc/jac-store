package com.yph.controller.schedule;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.schedule.ScheduleJobLog;
import com.yph.service.schedule.IScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 *   调度日志控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/22
 **/
@RestController
@RequestMapping("sys/schedule/log")
public class ScheduleJobLogController {

    @Autowired
    private IScheduleJobLogService scheduleJobLogService;

    /**
     *  分页查询
     * @param params
     * @return
     */
    @RequestMapping("findScheduleJobLogByPage")
    public CommonResult findScheduleJobLogByPage(@RequestParam HashMap<String,Object> params,Integer pageNum,Integer pageSize){
        PageInfo pageInfo = scheduleJobLogService.findScheduleJobLogByPage(params,pageNum,pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }


    /**
     *  查询一条记录
     * @return
     */
    @RequestMapping("findScheduleJobLogById")
    public CommonResult findScheduleJobLogById(Long id){
        ScheduleJobLog scheduleJobLog = scheduleJobLogService.findScheduleJobLog(id);
        return CommonResult.SUCCESS(scheduleJobLog);
    }

    /**
     *删除指定数据
     * @param id
     * @return
     */
    @RequestMapping("delScheduleJobLog")
    public CommonResult delScheduleJobLog(Long id){
        Assert.notNull(id,"");
        int result =   scheduleJobLogService.delScheduleJobLog(id);
      if(result<1){
          return CommonResult.ERROR();
      }
      return CommonResult.SUCCESS();
    }


    /**
     *  批量删除
     * @param request
     * @return
     */
    @RequestMapping("batchDelScheduleJobLog")
    public CommonResult batchDelScheduleJobLog(HttpServletRequest request){
        Long [] ids = new Long[10];
        int result =   scheduleJobLogService.batchDelScheduleJobLog(ids);
        if(result<1){
            return CommonResult.ERROR();
        }
        return CommonResult.SUCCESS();
    }



}
