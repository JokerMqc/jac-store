package com.yph.entity.dict;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysDictType implements Serializable {

    private Long id;

    private String name;

    private Byte flag;

    private Integer sort;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}