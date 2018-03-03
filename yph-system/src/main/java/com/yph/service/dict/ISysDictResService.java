package com.yph.service.dict;

import com.github.pagehelper.PageInfo;
import com.yph.entity.dict.SysDictRes;
import com.yph.entity.tree.ZtreeVo;

import java.util.HashMap;
import java.util.List;

public interface ISysDictResService {

    PageInfo findSysDictResListByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize);


    int delSysDictResById(Long id);


    int updateSysDictRes(SysDictRes sysDictRes);


    int saveSysDictRes(SysDictRes sysDictRes);


    int batchDelSysDictRes(Long[] ids);

    List<SysDictRes> findSysDictResListByTypeId(Long typeId);


    List<ZtreeVo> findAllSysDictResByZtree();

    SysDictRes findSysDictResOne(Long id);

    SysDictRes findSysDictResOneByCode(String resCode);


}
