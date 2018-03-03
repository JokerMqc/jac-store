package com.yph.entity.mail;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  邮件模板
 */
@Data
public class SysMailTemplate implements Serializable {

    /**
     *  模板Id
     */
    private Long id;

    /**
     *  模板名称
     */
    private String name;

    /**
     *  邮件主题
     */
    private String subject;

    /**
     *  邮件内容
     */
    private String content;

    /**
     *  删除标记  0： 未删除 1：删除
     */
    private Byte flag;

    /**
     *  模板类型
     */
    private Long type;

    /**
     *  模板状态
     */
    private Byte statusFlag;

    /**
     *  创建时间
     */
    private Date createTime;


    private static final long serialVersionUID = 1L;


}