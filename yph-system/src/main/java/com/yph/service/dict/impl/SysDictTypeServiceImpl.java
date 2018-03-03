package com.yph.service.dict.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.common.utils.Page;
import com.yph.entity.dict.SysDictType;
import com.yph.entity.tree.ZtreeVo;
import com.yph.mapper.dict.SysDictTypeMapper;
import com.yph.service.dict.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 字典类型
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    /**
     * 保存字典类型
     *
     * @param sysDictType
     * @return
     */
    @Override
    public int saveSysDictType(SysDictType sysDictType) {
        return sysDictTypeMapper.insert(sysDictType);
    }

    /**
     * 删除字典类型
     *
     * @param id
     * @return
     */
    @Override
    public int delSysDictTypeById(Long id) {
        return sysDictTypeMapper.delSysDictTypeById(id);
    }

    /**
     * 更新字典类型
     *
     * @param sysDictType
     * @return
     */
    @Override
    public int updateSysDictType(SysDictType sysDictType) {
        return sysDictTypeMapper.updateByPrimaryKeySelective(sysDictType);
    }

    /**
     * 分页获取字典类型
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findSysDictTypeListByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(" create_time desc ");
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);

        List<SysDictType> list = sysDictTypeMapper.findSysDictTypeListByPage(params);
        PageInfo<SysDictType> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     *  获取所有的字典类型
     * @return
     */
    @Override
    public List<ZtreeVo> findAllSysDictTypeListByZtree() {
        return sysDictTypeMapper.findAllSysDictTypeListByZtree();
    }

    /**
     *  通过名称查询
     * @param name
     * @return
     */
    @Override
    public SysDictType findSysDictTypeOneByName(String name) {
        return sysDictTypeMapper.findSysDictTypeOneByName(name);
    }
}
