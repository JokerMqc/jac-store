package com.yph.service.log;

import com.github.pagehelper.PageInfo;
import com.yph.entity.log.SysExceptionLog;

import java.util.HashMap;
import java.util.List;

/**
 * 异常日志
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/19
 **/
public interface ISysExceptionLogService {

    int saveSysExceptionLog(SysExceptionLog sysExceptionLog);

    int delSysExceptionLogById(Long Id);

    int updateSysExceptionLog(SysExceptionLog sysExceptionLog);

    List<SysExceptionLog> findSysExceptionLogList(SysExceptionLog sysExceptionLog);

    PageInfo findSysExceptionLogByPage(HashMap<String,Object> params,Integer pageNum,Integer pageSize);

}
