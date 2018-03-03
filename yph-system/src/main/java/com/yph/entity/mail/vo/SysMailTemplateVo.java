package com.yph.entity.mail.vo;

import com.yph.entity.mail.SysMailTemplate;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件模版 Vo
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Data
public class SysMailTemplateVo extends SysMailTemplate implements Serializable{

    private static final long serialVersionUID = 2706208773159356294L;

//    /**
//     *  模板Id
//     */
//    private Long id;
//
//    /**
//     *  模板名称
//     */
//    private String name;
//
//    /**
//     *  邮件主题
//     */
//    private String subject;
//
//    /**
//     *  邮件内容
//     */
//    private String content;
//
//    /**
//     *  删除标记  0： 未删除 1：删除
//     */
//    private Byte flag;

//    /**
//     *  模板类型
//     */
//    private Integer type;
//
//    /**
//     *  模板状态
//     */
//    private Byte statusFlag;
//
//    /**
//     *  创建时间
//     */
//    private Date createTime;

    /**
     *  模版类型
     */
    private String TypeName;



}
