package com.yph.entity.dict;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDictRes implements Serializable {
    private Long id;

    private Long typeId;

    private String name;

    private Byte flag;

    private String code;

    private Integer sort;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}