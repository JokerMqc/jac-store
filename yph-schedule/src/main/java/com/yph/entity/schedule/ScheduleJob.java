package com.yph.entity.schedule;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务id
     */
    private Long Id;


    /**
     * 任务名称
     */
    private String jobName;


    /**
     * spring bean名称
     */
    @NotBlank(message = "bean名称不能为空")
    private String beanName;

    /**
     * 方法名
     */
    @NotBlank(message = "方法名称不能为空")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;


    /**
     * 删除标记
     */
    private Byte flag;


    /**
     * 任务状态  0:运行中 1:暂停中
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;


}