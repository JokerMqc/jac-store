package com.yph.mapper.mail;

import com.yph.entity.mail.SysMailTemplate;
import com.yph.entity.mail.vo.SysMailTemplateVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysMailTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMailTemplate record);

    int insertSelective(SysMailTemplate record);

    SysMailTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMailTemplate record);

    int updateByPrimaryKey(SysMailTemplate record);

    List<SysMailTemplateVo> findSysMailTemplateByPage(HashMap<String, Object> params);



    /**
     *  获取默认的模版
     * @return
     */
    SysMailTemplate findSysMailTemplateByDefault(@Param("type") Long type);

    int selectDefaultTemplageById(@Param("id") Long id);

    int cancelDefaultTemplageById(@Param("id") Long id);

    SysMailTemplate findSysMailTemplateDefaultByType(@Param("type") Long type);

}