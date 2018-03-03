package com.yph.service.dict;

import com.github.pagehelper.PageInfo;
import com.yph.entity.dict.SysDictType;
import com.yph.entity.tree.ZtreeVo;

import java.util.HashMap;
import java.util.List;

public interface ISysDictTypeService {

    /**
     *  保存字典类型
     * @param sysDictType
     * @return
     */
    int saveSysDictType(SysDictType sysDictType);

    /**
     *  删除字典类型 通过id
     * @param id
     * @return
     */
    int delSysDictTypeById(Long id);

    /**
     *  更新字典类型
     * @param sysDictType
     * @return
     */
    int updateSysDictType(SysDictType sysDictType);


    /**
     *  分页获取字典类型
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findSysDictTypeListByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize);


    List<ZtreeVo> findAllSysDictTypeListByZtree();


    SysDictType findSysDictTypeOneByName(String name);


}
