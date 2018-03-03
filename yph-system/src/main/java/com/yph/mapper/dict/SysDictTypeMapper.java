package com.yph.mapper.dict;

import com.yph.entity.dict.SysDictType;
import com.yph.entity.tree.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysDictTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);

    int delSysDictTypeById(@Param("id") Long id);

    List<SysDictType> findSysDictTypeListByPage(HashMap<String, Object> params);

    List<ZtreeVo> findAllSysDictTypeListByZtree();

    SysDictType findSysDictTypeOneByName(@Param("name") String name);
}