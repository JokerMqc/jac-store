package com.yph.mapper.schedule;

import com.yph.entity.schedule.ScheduleJobLog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ScheduleJobLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScheduleJobLog record);

    int insertSelective(ScheduleJobLog record);

    ScheduleJobLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScheduleJobLog record);

    int updateByPrimaryKey(ScheduleJobLog record);

    int delBefore3Day(HashMap<String,Object> params);


    List<ScheduleJobLog> findScheduleJobLogByPage(HashMap<String, Object> params);

    int delScheduleJobLogById(@Param("id") Long id);

    int batchDelScheduleJobLog(Long[] ids);
}