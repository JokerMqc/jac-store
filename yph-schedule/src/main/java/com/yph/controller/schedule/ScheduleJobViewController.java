package com.yph.controller.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 定时任务 控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/23
 **/
@Controller
@RequestMapping("sys/schedule/view/")
public class ScheduleJobViewController {

    /**
     *  跳转到定时任务列表页面
     * @return
     */
    @RequestMapping("toScheduleListView")
    public String toScheduleListView(){
        return "sys/schedule/sys_schedule_list";
    }

    /**
     *  跳转到定时任务日志列表页面
     * @return
     */
    @RequestMapping("toScheduleLogListView")
    public String toScheduleLogListView(){
        return "sys/schedule/sys_schedule_log_list";
    }



}
