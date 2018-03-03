package com.yph.entity.log;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysExceptionLog implements Serializable {

    private Long id;

    private String calzzName;

    private String methodName;

    private String exceptionMsg;

    private Byte flag;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}