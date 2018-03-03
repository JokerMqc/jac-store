package com.yph.service.log.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.entity.log.SysExceptionLog;
import com.yph.mapper.log.SysExceptionLogMapper;
import com.yph.service.log.ISysExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统异常日志
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/19
 **/
@Slf4j
@Service
public class SysExceptionLogServiceImpl implements ISysExceptionLogService {

    @Autowired
    private SysExceptionLogMapper sysExceptionLogMapper;


    @Override
    public int saveSysExceptionLog(SysExceptionLog sysExceptionLog) {
        sysExceptionLog.setCreateTime(new Date());
        sysExceptionLog.setFlag(new Byte("0"));
        return sysExceptionLogMapper.insert(sysExceptionLog);
    }

    @Override
    public int delSysExceptionLogById(Long id) {
        return sysExceptionLogMapper.delSysExceptionLogById(id);
    }

    @Override
    public int updateSysExceptionLog(SysExceptionLog sysExceptionLog) {
        return sysExceptionLogMapper.updateByPrimaryKeySelective(sysExceptionLog);
    }

    @Override
    public List<SysExceptionLog> findSysExceptionLogList(SysExceptionLog sysExceptionLog) {
        return sysExceptionLogMapper.findSysExceptionLogList(sysExceptionLog);
    }

    @Override
    public PageInfo findSysExceptionLogByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy(" create_time desc ");
        List<SysExceptionLog> list =  sysExceptionLogMapper.findSysExceptionLogByPage(params);
        PageInfo<SysExceptionLog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
