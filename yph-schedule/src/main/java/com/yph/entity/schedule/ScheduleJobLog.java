package com.yph.entity.schedule;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleJobLog implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 日志id
     */
    private Long Id;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     *  任务名称
     */
    private String jobName;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;

}