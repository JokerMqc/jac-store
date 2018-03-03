package com.yph.mapper.log;

import com.yph.entity.log.SysExceptionLog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysExceptionLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysExceptionLog record);

    int insertSelective(SysExceptionLog record);

    SysExceptionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysExceptionLog record);

    int updateByPrimaryKey(SysExceptionLog record);

    int delSysExceptionLogById(@Param("id") Long id);

    List<SysExceptionLog> findSysExceptionLogList(SysExceptionLog sysExceptionLog);

    List<SysExceptionLog> findSysExceptionLogByPage(HashMap<String, Object> params);


}