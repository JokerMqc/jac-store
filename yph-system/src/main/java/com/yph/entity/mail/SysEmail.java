package com.yph.entity.mail;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysEmail implements Serializable {

    private Long id;

    private String fromEmail;

    private String fromName;

    private String toEmails;

    private String subject;

    private String context;

    private Byte flag;

    private Byte sendStatus;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}