package com.yph.mapper.dict;

import com.yph.entity.dict.SysDictRes;
import com.yph.entity.tree.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysDictResMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDictRes record);

    int insertSelective(SysDictRes record);

    SysDictRes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictRes record);

    int updateByPrimaryKey(SysDictRes record);

    List<SysDictRes> findSysDictResListByPage(HashMap<String, Object> params);

    int delSysDictResById(Long id);

    List<SysDictRes> findSysDictResListByTypeId(Long typeId);

    List<ZtreeVo> findSysDictResListByZtree(@Param("typeId") Long typeId);

    SysDictRes findSysDictResOneByCode(@Param("code") String code);
}