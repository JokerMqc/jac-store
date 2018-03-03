package com.yph.mapper.schedule;

import com.yph.entity.schedule.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleJobMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    ScheduleJob findScheduleJobById(@Param("id") Long id);

    List<ScheduleJob> findListScheduleJob(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int deleteBatch(Long[] jobIds);

    int updateBatch(Map<String, Object> map);

    List<ScheduleJob> findScheduleJobByPage(Map<String, Object> params);

    int delScheduleJobById(@Param("id") Long id);

    int updateScheduleJobStatus(@Param("status") int status);

}