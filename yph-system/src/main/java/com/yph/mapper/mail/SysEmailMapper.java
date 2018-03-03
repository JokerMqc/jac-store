package com.yph.mapper.mail;


import com.yph.entity.mail.SysEmail;

import java.util.HashMap;
import java.util.List;

public interface SysEmailMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysEmail record);

    int insertSelective(SysEmail record);

    SysEmail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysEmail record);

    int updateByPrimaryKey(SysEmail record);

    List<SysEmail> findMailListByPage(HashMap<String, Object> params);
}